package com.learn.ssm.chapter22.dao;

import com.learn.ssm.chapter22.pojo.RedPacket;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RedPacketDao {
    /**
     * 　获取红包信息
     * @param id　红包ｉｄ
     * @return 红包具体信息
     */
    RedPacket getRedPacket(Long id);

    /**     使用悲观锁来保证高并发的安全性
     * 　获取红包信息
     * @param id　红包ｉｄ
     * @return 红包具体信息
     */
    RedPacket getRedPacketForUpdate(Long id);

    /**
     * 　扣减抢红包数
     * @param id 红包ｉｄ
     * @return 更新记录条数
     */
    int decreaseRedPacket(Long id);

    /**
     * 　 通过版本号扣减抢红包，
     *         每更新一次，版本增一
     *         其次增加对版本号的判断
     * @param redPacketId 红包ｉｄ
     * @return 更新记录条数
     */
    int decreaseRedPacketForVersion(@Param("id") Long redPacketId, @Param("version") Integer version);
}
