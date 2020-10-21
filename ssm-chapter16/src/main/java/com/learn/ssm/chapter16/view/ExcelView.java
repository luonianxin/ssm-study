package com.learn.ssm.chapter16.view;

import lombok.Data;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Data
public class ExcelView extends AbstractXlsView {
    //　文件名
    private String fileName = null;

    //　导出视图自定义接口
    private ExcelExportService excelExportService = null;

    public ExcelView(ExcelExportService excelExportService){
        this.excelExportService = excelExportService;
    }

    public ExcelView(String viewName,ExcelExportService excelExportService){
        this.setBeanName(viewName);
    }
    /**
     * Application-provided subclasses must implement this method to populate
     * the Excel workbook document, given the model.
     *
     * @param model    the model Map
     * @param workbook the Excel workbook to populate
     * @param request  in case we need locale etc. Shouldn't look at attributes.
     * @param response in case we need to set cookies. Shouldn't write to it.
     */
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

            if(excelExportService == null){
                throw new RuntimeException("导出服务接口不能为空！！");
            }
            // 文件名不能为空，如果为空则使用请求路径中的字符串作为文件名
            if(!StringUtils.isEmpty(fileName)){
                String reqCharset = request.getCharacterEncoding();
                reqCharset = reqCharset == null ? "UTF-8" : reqCharset;
                fileName = new String(fileName.getBytes(reqCharset),"ISO8859-1");

            // 设置下面的文件名
            response.setHeader("Content-disposition","attachment;fileName="+fileName);
            }
            excelExportService.makeWorkBook(model, workbook);
    }
}
