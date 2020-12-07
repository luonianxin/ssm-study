package com.learn.ssm.chapter22.dao;

import com.learn.ssm.chapter22.pojo.RedPacket;
import org.springframework.stereotype.Repository;

@Repository
public interface RedPacketDao {
    /**
     * 　获取红包信息
     * @param id　红包ｉｄ
     * @return 红包具体信息
     */
    RedPacket getRedPacket(Long id);

    /**
     * 　扣减抢红包数
     * @param id 红包ｉｄ
     * @return 更新记录条数
     */
    int decreaseRedPacket(Long id);
}
