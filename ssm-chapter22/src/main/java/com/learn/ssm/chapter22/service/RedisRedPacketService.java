package com.learn.ssm.chapter22.service;

public interface RedisRedPacketService {
    void saveUserRedPacketByRedis(Long redPacketId, Double unitAmount);
}
