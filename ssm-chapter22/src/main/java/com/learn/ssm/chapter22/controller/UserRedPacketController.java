package com.learn.ssm.chapter22.controller;

import com.learn.ssm.chapter22.service.UserRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/userRedPacket")
public class UserRedPacketController {
    @Autowired
    private UserRedPacketService userRedPacketService = null;

    @ResponseBody
    @RequestMapping("/grepRedPacket")
    public Map<String,Object> grapRedPacket(Long redPacketId, Long userId){
        int result = userRedPacketService.grapRedPacket(redPacketId,userId);
        Map<String,Object> retMap = new HashMap<String,Object>();
        boolean flag = result>0;
        retMap.put("success",flag);
        retMap.put("message",flag? "grep RedPacket success":"grep RedPacket faild");
        return retMap;
    }


    @ResponseBody
    @RequestMapping("/grepRedPacketForUpdate")
    public Map<String,Object> getRedPacketForUpdate(Long redPacketId, Long userId){
        int result = userRedPacketService.grapRedPacket(redPacketId,userId);
        Map<String,Object> retMap = new HashMap<String,Object>();
        boolean flag = result>0;
        retMap.put("success",flag);
        retMap.put("message",flag? "grep RedPacket success":"grep RedPacket faild");
        return retMap;
    }

    @ResponseBody
    @RequestMapping("/grepRedPacketForVersion")
    public Map<String,Object> grepRedPacketForVersion(Long redPacketId, Long userId){
        int result = userRedPacketService.grapRedPacketForVersion(redPacketId,userId);
        Map<String,Object> retMap = new HashMap<String,Object>();
        boolean flag = result>0;
        retMap.put("success",flag);
        retMap.put("message",flag? "grep RedPacket success":"grep RedPacket faild");
        return retMap;
    }

    @ResponseBody
    @RequestMapping("/grapRedPacketForVersionRepeatForWhile")
    public Map<String,Object> grapRedPacketForVersionRepeatForWhile(Long redPacketId, Long userId){
        int result = userRedPacketService.grapRedPacketForVersionRepeatForWhile(redPacketId,userId);
        Map<String,Object> retMap = new HashMap<String,Object>();
        boolean flag = result>0;
        retMap.put("success",flag);
        retMap.put("message",flag? "grep RedPacket success":"grep RedPacket faild");
        return retMap;
    }

    @ResponseBody
    @RequestMapping("/grapRedPacketForVersionRepeatForFrequency")
    public Map<String,Object> grapRedPacketForVersionRepeatForFrequency(Long redPacketId, Long userId){
        int result = userRedPacketService.grapRedPacketForVersionRepeatForFrequency(redPacketId,userId);
        Map<String,Object> retMap = new HashMap<String,Object>();
        boolean flag = result>0;
        retMap.put("success",flag);
        retMap.put("message",flag? "grep RedPacket success":"grep RedPacket faild");
        return retMap;
    }
}
