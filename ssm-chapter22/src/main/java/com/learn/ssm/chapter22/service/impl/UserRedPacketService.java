package com.learn.ssm.chapter22.service.impl;

import com.learn.ssm.chapter22.dao.RedPacketDao;
import com.learn.ssm.chapter22.dao.UserRedPacketDao;
import com.learn.ssm.chapter22.pojo.RedPacket;
import com.learn.ssm.chapter22.pojo.UserRedPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRedPacketService implements com.learn.ssm.chapter22.service.UserRedPacketService {

    @Autowired
    private UserRedPacketDao userRedPacketDao = null;

    @Autowired
    private RedPacketDao redPacketDao = null;

    //　失败
    private static final int FAILED = 0;


    /**
     * 　保存抢红包信息
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
        if(redPacket.getStock() > 0){
            redPacketDao.decreaseRedPacket(redPacketId);
            // 生成抢红包信息
            UserRedPacket userRedPacket = new UserRedPacket();
            userRedPacket.setRedPacketId(redPacketId);
            userRedPacket.setAmount(redPacket.getUnitAmount());
            userRedPacket.setUserId(userId);
            userRedPacket.setNote("抢红包"+ redPacketId);
            int result = userRedPacketDao.grapRedPacket(userRedPacket);
            return result;
        }
        // 失败返回
        return FAILED;
    }
}
