package com.learn.ssm.chapter22.service.impl;

import com.learn.ssm.chapter22.dao.RedPacketDao;
import com.learn.ssm.chapter22.pojo.RedPacket;
import com.learn.ssm.chapter22.service.RedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RedPacketServiceImpl implements RedPacketService {

    @Autowired
    private RedPacketDao redPacketDao;

    /**
     * 　获取红包
     *
     * @param id 　编号
     * @return 红包信息
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public RedPacket getRedPacket(Long id) {
        return redPacketDao.getRedPacket(id);
    }

    /**
     * 扣减红包
     *
     * @param id 　编号
     * @return 影响条数
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int decreaseRedPacket(Long id) {
        return redPacketDao.decreaseRedPacket(id);
    }
}
