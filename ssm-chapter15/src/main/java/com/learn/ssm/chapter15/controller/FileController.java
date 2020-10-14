package com.learn.ssm.chapter15.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 　文件上传控制器，练习使用spring mvc 上传文件
 */

@Controller
@RequestMapping("/file")
public class FileController {

    @RequestMapping("/form")
    public ModelAndView form(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("fileUploadExample");
        return mv;
    }

    @RequestMapping("/upload")
    public ModelAndView upload(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        // httpSevletRequest 转换成 MultipartHttpServletRequest
        MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request;
        // get the file
        MultipartFile file = mhsr.getFile("file");
        mav.setView(new MappingJackson2JsonView());
        String fileName = file.getOriginalFilename();
        File dest = new File(fileName);
        try{
            // save the file
            file.transferTo(dest);
            // save success return msg
            mav.addObject("success",true);
            mav.addObject("msg","上传成功");

        }catch (IllegalStateException|IOException e){
            mav.addObject("success",false);
            mav.addObject("msg","文件上传失败");
            e.printStackTrace();
        }
        return mav;
    }
}
