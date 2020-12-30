package com.learn.ssm.chapter22.service.impl;

import com.learn.ssm.chapter22.dao.RedPacketDao;
import com.learn.ssm.chapter22.dao.UserRedPacketDao;
import com.learn.ssm.chapter22.pojo.RedPacket;
import com.learn.ssm.chapter22.pojo.UserRedPacket;
import com.learn.ssm.chapter22.service.RedisRedPacketService;
import com.learn.ssm.chapter22.service.UserRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

@Service
public class UserRedPacketServiceImpl implements UserRedPacketService {

    @Autowired
    private UserRedPacketDao userRedPacketDao = null;

    @Autowired
    private RedPacketDao redPacketDao = null;

    @Autowired
    private RedisTemplate redisTemplate = null;

    @Autowired
    private RedisRedPacketService redisRedPacketService = null;
    //　失败
    private static final int FAILED = 0;


    /**
     * 　保存抢红包信息
     *
     * @param redPacketId 红包编号
     * @param userId      抢红包用户编号
     * @return 影响记录数
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int grapRedPacket(Long redPacketId, Long userId) {

        //　获取红包信息
        RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
        // 当前红包库存大于0
        if (redPacket.getStock() > 0) {
            redPacketDao.decreaseRedPacket(redPacketId);
            // 生成抢红包信息
            UserRedPacket userRedPacket = new UserRedPacket();
            userRedPacket.setRedPacketId(redPacketId);
            userRedPacket.setAmount(redPacket.getUnitAmount());
            userRedPacket.setUserId(userId);
            userRedPacket.setNote("抢红包" + redPacketId);
            int result = userRedPacketDao.grapRedPacket(userRedPacket);
            return result;
        }
        // 失败返回
        return FAILED;
    }

    /**
     * 　保存抢红包信息
     * 使用乐观锁的方式实现抢红包
     *
     * @param redPacketId 红包编号
     * @param userId      抢红包用户编号
     * @return 影响记录数
     */

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int grapRedPacketForVersion(Long redPacketId, Long userId) {

        //　获取红包信息
        RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
        // 当前红包库存大于0
        if (redPacket.getStock() > 0) {
            int update = redPacketDao.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());

            if (update == 0) {
                return FAILED;
            }

            redPacketDao.decreaseRedPacket(redPacketId);
            // 生成抢红包信息
            UserRedPacket userRedPacket = new UserRedPacket();
            userRedPacket.setRedPacketId(redPacketId);
            userRedPacket.setAmount(redPacket.getUnitAmount());
            userRedPacket.setUserId(userId);
            userRedPacket.setNote("抢红包" + redPacketId);

            //　抢红包
            int result = userRedPacketDao.grapRedPacket(userRedPacket);
            return result;
        }
        // 失败返回
        return FAILED;
    }


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int grapRedPacketForVersionRepeatForWhile(Long redPacketId, Long userId) {

        long start = System.currentTimeMillis();
        while (true) {
            long end = System.currentTimeMillis();
            if (end - start > 100) {
                return FAILED;
            }
            //　获取红包信息
            RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
            // 当前红包库存大于0
            if (redPacket.getStock() > 0) {
                //  再次传入线程保存的version　旧值,给ｓｑｌ判断是否有其他线程修改过数据
                int update = redPacketDao.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());

                // 如果没有数据更新那么说明数据已被其他线程修改过，重新抢夺
                if (update == 0) {
                    continue;
                }

                redPacketDao.decreaseRedPacket(redPacketId);
                // 生成抢红包信息
                UserRedPacket userRedPacket = new UserRedPacket();
                userRedPacket.setRedPacketId(redPacketId);
                userRedPacket.setAmount(redPacket.getUnitAmount());
                userRedPacket.setUserId(userId);
                userRedPacket.setNote("抢红包" + redPacketId);

                //　抢红包
                int result = userRedPacketDao.grapRedPacket(userRedPacket);
                return result;
            } else {
                // 一旦没有库存，立即返回失败
                return FAILED;
            }
        }

    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int grapRedPacketForVersionRepeatForFrequency(Long redPacketId, Long userId) {

            for (int i = 0; i < 3; i++) {
                //　获取红包信息
                RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
                // 当前红包库存大于0
                if (redPacket.getStock() > 0) {
                    //  再次传入线程保存的version　旧值,给ｓｑｌ判断是否有其他线程修改过数据
                    int update = redPacketDao.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());

                    // 如果没有数据更新那么说明数据已被其他线程修改过，重新抢夺
                    if (update == 0) {
                        continue;
                    }
                    redPacketDao.decreaseRedPacket(redPacketId);
                    // 生成抢红包信息
                    UserRedPacket userRedPacket = new UserRedPacket();
                    userRedPacket.setRedPacketId(redPacketId);
                    userRedPacket.setAmount(redPacket.getUnitAmount());
                    userRedPacket.setUserId(userId);
                    userRedPacket.setNote("抢红包" + redPacketId);

                    //　抢红包
                    int result = userRedPacketDao.grapRedPacket(userRedPacket);
                    return result;
                } else {
                    // 一旦没有库存，立即返回失败
                    return FAILED;
                }
            }
            return FAILED;
        }
     String script = "local listKey = 'red_packet_list_'..KEYS[1]\n"
             +"local redPacket = 'red_packet_'..KEYS[1]\n"
             +"local stock = tonumber(redis.call('hget',redPacket,'stock'))\n"
             +"if stock <= 0 then return 0  end\n"
             + "stock = stock -1 \n"
             +"redis.call('hset', redPacket,'stock',tostring(stock))\n"
             +"redis.call('rpush',listKey,ARGV[1])\n"
             +"if(stock == 0) then return 2 end \n"
             +"return 1 \n";

    // 保存ｒｅｄｉｓ返回的３２位sha1 编码，通过它去执行缓存的lua脚本
    String sha1 = null;
    @Override
    public Long grapRedPacketByRedis(Long redPacketId, Long userId) {

        // 当前抢红包的用户和日期信息
        String args = userId+"-"+System.currentTimeMillis();
        Long result = null;
        Jedis jedis = (Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        try{
            if(sha1 == null){
                sha1 = jedis.scriptLoad(script);
            }
            // execute script and return the result
            Object res = jedis.evalsha(sha1,1,redPacketId+"",args);
            result = (Long) res;
            if(result == 2){
                String unitAmountStr = jedis.hget("red_packet_"+redPacketId,"unit_amount");
                // 触发保存操作
                Double unitAmount = Double.parseDouble(unitAmountStr);
                System.err.println("thread _name   =  "+Thread.currentThread().getState());
                redisRedPacketService.saveUserRedPacketByRedis(redPacketId,unitAmount);
            }
        }finally {
            // close resources
            if( jedis != null && jedis.isConnected()){
                jedis.close();
            }
        }
        return result;
    }
}
