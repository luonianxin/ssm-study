package com.learn.ssm.chapter22.service.impl;

import com.learn.ssm.chapter22.pojo.UserRedPacket;
import com.learn.ssm.chapter22.service.RedisRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class RedisRedPacketServiceImpl implements RedisRedPacketService {

    private static final String PREFIX = "red_packet_list_";
    // 　每次取出1000　条,避免一次取出消耗太多内存
    private static final int TIME_SIZE = 1000;

    @Autowired
    private RedisTemplate template;

    @Autowired
    private DataSource dataSource;

    @Override
    //  开启新的线程运行
    @Async
    public void saveUserRedPacketByRedis(Long redPacketId, Double unitAmount) {
        System.err.println("start to save data");

        Long start = System.currentTimeMillis();
        // get listOprated object
        BoundListOperations ops = template.boundListOps(PREFIX+redPacketId);
        Long size = ops.size();
        Long times = size %TIME_SIZE == 0? size/TIME_SIZE :size/TIME_SIZE+1;
        int count = 0;
        List<UserRedPacket> userRedPacketList = new ArrayList<>(TIME_SIZE);
        for(int i=0; i<times; i++){
            // 获取最多TIME_SIZE个红包信息
            List userIdList = null;
            if(i == 0){
                userIdList = ops.range(i*TIME_SIZE,(i+1)*TIME_SIZE);
            }else{
                userIdList = ops.range(i*TIME_SIZE+1,(i+1)*TIME_SIZE);
            }
            userRedPacketList.clear();
            for(int j=0; j<userIdList.size(); j++){
                String args = userIdList.get(j).toString();
                String [] arr = args.split("-");
                String userIdStr = arr[0];
                String timeStr = arr[1];
                Long userId = Long.parseLong(userIdStr);
                Long time = Long.parseLong(timeStr);
                // generate redPacket info
                UserRedPacket userRedPacket = new UserRedPacket();
                userRedPacket.setRedPacketId(redPacketId);
                userRedPacket.setAmount(unitAmount);
                userRedPacket.setUserId(userId);
                userRedPacket.setGrabTime(new Timestamp(time));
                userRedPacket.setNote("抢红包-"+redPacketId);
                userRedPacketList.add(userRedPacket);
            }
            count +=  executeBatch(userRedPacketList);
        }
        template.delete(PREFIX+redPacketId);
        Long end = System.currentTimeMillis();
        System.err.println("保存数据结束,　"+ (end - start) +" ms,共"+count+" 条记录被保存．");

    }

    /**
     * 使用ＪＤＢＣ批量处理ｒｅｄｉｓ缓存数据
     * @param userRedPacketList　－－－　抢红包列表
     * @return 抢红包插入数量
     */
    private int executeBatch(List<UserRedPacket> userRedPacketList){
        Connection conn = null;
        Statement stmt = null;
        int count[] = null;
        try{
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            for(UserRedPacket userRedPacket : userRedPacketList){
                String sql1 = "update T_RED_PACKET set stock = stock-1 where id="+userRedPacket.getRedPacketId();
                DateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sql2 = "insert into T_USER_RED_PACKET(red_packet_id,user_id,amount,grab_time,note)" +
                        "values("+userRedPacket.getRedPacketId()+" ," +
                        userRedPacket.getUserId()+" ,"
                        + userRedPacket.getAmount()+" ,"
                        + "'"+fm.format(userRedPacket.getGrabTime())+"',"
                        + "'"+userRedPacket.getNote()+"')";
                stmt.addBatch(sql1);
                stmt.addBatch(sql2);
            }
            count = stmt.executeBatch();
            conn.commit();
        }catch (SQLException e){
            /** 错误处理逻辑**/
            throw new RuntimeException("批量处理抢红包逻辑出错");
        }finally{
            try {
                if(conn != null && !(conn.isClosed())){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return count.length/2;
    }
}
