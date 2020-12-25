package com.learn.ssm.chapter22.service;

public interface UserRedPacketService {
    /**
     * 　保存抢红包信息
     * @param redPacketId 红包编号
     * @param userId 抢红包用户编号
     * @return 影响记录数
     */
    int grapRedPacket(Long redPacketId,Long userId);


    /**
     * 　使用乐观锁实现抢红包
     * @param redPacketId　红包ｉｄ
     * @param userId 用户ｉｄ
     * @return 成功结果
     */
    int grapRedPacketForVersion(Long redPacketId,Long userId);

    /**
     * 　乐观锁重入方式一,一段时间内允许重试100ms
     *     优点:提高了本案例中的抢红包成功率
     *     缺点:时间戳不稳定,
     * @param redPacketId 红包ｉｄ
     * @param userId 用户ｉｄ
     * @return 抢红包的结果
     */
    int grapRedPacketForVersionRepeatForWhile(Long redPacketId,Long userId);

    /**
     * 　乐观锁重入方式二,限制重入次数比如3次
     *     优点:提高了本案例中的抢红包成功率
     *     缺点:时间戳不稳定,
     * @param redPacketId 红包ｉｄ
     * @param userId 用户ｉｄ
     * @return 抢红包的结果
     */
    int grapRedPacketForVersionRepeatForFrequency(Long redPacketId,Long userId);

}
