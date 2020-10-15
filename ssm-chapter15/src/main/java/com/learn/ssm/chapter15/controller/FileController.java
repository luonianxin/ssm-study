package com.learn.ssm.chapter15.controller;

import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
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

    /***
     *  使用原始的　httpServletRequest  转换成　MultipartHttpServletRequest
     * @param request  原始ａｐｉ　请求对象
     * @return ｊｓｏｎ视图对象
     */
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


    /**
     * 　通过ｓｐｒｉｎｇ　ｍｖｃ　提供的　multipartFile 来操作文件
     * @param file
     * @return
     */
    @RequestMapping("/uploadByMultipartFile")
    public ModelAndView uploadByMultipartRequest(MultipartFile file){
        ModelAndView mav = new ModelAndView();
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

    /**
     *  通过ｓｅｒｖｌｅｔ　　api　的
     *  Ｐａｒｔ　类来上传文件
     * @param part　实际上传对象,实验总是为null，不会被ｓｐｒｉｎｇ　ｍｖｃ 初始化
     * @return 视图对象
     */
    @RequestMapping("/uploadByPart")
    public ModelAndView uploadByPart(Part part){
        ModelAndView mv = new ModelAndView();
        String fileName = part.getSubmittedFileName();

        try{
            part.write("/home/lnx/Desktop/"+fileName);
            mv.addObject("success",true);
            mv.addObject("msg","上传成功");

        }catch (IllegalStateException|IOException e){

            mv.addObject("success",false);
            mv.addObject("msg","文件上传失败");
            e.printStackTrace();
        }
        return mv;
    }
}
