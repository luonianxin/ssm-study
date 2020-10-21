package com.learn.ssm.chapter16.view;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.Map;

/**
 *  excel 导出生成功能
 */
public interface ExcelExportService {

    /**
     * 　生成excel 文件的规则
     * @param model 数据模型
     * @param workbook excel workbook
     */
    void makeWorkBook(Map<String, Object> model, Workbook workbook);
}
