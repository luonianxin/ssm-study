package com.learn.ssm.chapter22.service;

import com.learn.ssm.chapter22.pojo.RedPacket;
import org.springframework.stereotype.Repository;


public interface RedPacketService {
    /**
     * 　获取红包
     * @param id　编号
     * @return 红包信息
     */
    RedPacket getRedPacket(Long id);

    /**
     * 　获取红包
     * @param id　编号
     * @return 红包信息
     */
    RedPacket getRedPacketForUpdate(Long id);

    /**
     *  扣减红包
     * @param id　编号
     * @return 影响条数
     */
    int decreaseRedPacket(Long id);
}
