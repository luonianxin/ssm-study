package com.learn.ssm.chapter15.controller;

import com.learn.ssm.chapter15.pojo.PageParam;
import com.learn.ssm.chapter15.pojo.Role;
import com.learn.ssm.chapter15.pojo.RoleParams;
import com.learn.ssm.chapter15.service.RoleService;
import com.learn.ssm.chapter15.view.ExcelExportService;
import com.learn.ssm.chapter15.view.ExcelView;
import com.mysql.fabric.xmlrpc.base.Params;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/view")
public class ViewController {

    @Autowired
    RoleService roleService ;

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public ModelAndView export() {
        ModelAndView mv = new ModelAndView();
        ExcelView ev = new ExcelView(exportService());
        ev.setFileName("所有角色.xlsx");
        PageParam pageParam = new PageParam();
        pageParam.setStart(0);
        pageParam.setLimit(10000);
        List<Role> roleList = roleService.findRoles(pageParam.getStart(),pageParam.getLimit());
        mv.addObject("roleList",roleList);
        mv.setView(ev);
        return mv;
    }


    private ExcelExportService exportService() {
            return (Map<String,Object> model,Workbook workbook)->{
                // 获取用户角色列表
                List<Role> roleList = (List<Role>) model.get("roleList");
                // 生成Ｓｈｅｅｔ　
                Sheet sheet = workbook.createSheet("所有角色");
                // 加载标题
                Row title = sheet.createRow(0);
                title.createCell(0).setCellValue("编号");
                title.createCell(1).setCellValue("名称");
                title.createCell(2).setCellValue("备注");
                title.createCell(3).setCellValue("创建日期");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                // 遍历角色列表,生成一行行数据
                for(int i = 0,j = roleList.size();i < j ; i++){
                     Role role = roleList.get(i);
                     int rowIdx = i+1;
                     Row row = sheet.createRow(rowIdx);
                     row.createCell(0).setCellValue(role.getId());
                     row.createCell(1).setCellValue(role.getRoleName());
                     row.createCell(2).setCellValue(role.getNote());
                     if(role.getCreateDate() != null)
                     row.createCell(3).setCellValue(simpleDateFormat.format(role.getCreateDate()));
                }
            };
    }
}
