package com.learn.ssm.chapter22.dao;

import com.learn.ssm.chapter22.pojo.UserRedPacket;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRedPacketDao {
    /**
     * 　插入抢红包嘻嘻你
     * @param userRedPacket 抢红包信息
     * @return 影响记录数
     */
    int grapRedPacket(UserRedPacket userRedPacket);
}
