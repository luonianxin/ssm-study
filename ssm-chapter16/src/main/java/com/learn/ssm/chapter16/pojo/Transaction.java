package com.learn.ssm.chapter16.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.Controller;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * 　验证表单字段 jsr３０３校验用的bean名称
 */
@Data
public class Transaction {

    @NotNull(message = "产品编号不能为空")
    private Long productId;
    @NotNull(message = "用户编号不能为空")
    private Long userId;
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @NotNull
    private Date date;
    @NotNull
    @DecimalMin(value = "0.1")
    private Double price;
    @Min(value = 1,message = "购买数量最低为1")
    @Max(value = 100,message = "最大购买数量不能超过100")
    @NotNull(message = "购买数量不能为空")
    private Integer quantity;

    @NotNull
    @DecimalMax(value = "500000.00")
    @DecimalMin(value = "1.00")
    private Double amount;

    @Pattern(regexp = "^([a-zA-Z0-9_\\.-]+)@([\\da-zA-Z\\.-]+)\\.([a-zA-Z\\.]{2,6})$",message = "邮件格式不合法")
    private String email;

    @Size(min = 0,max = 256)
    private String note;
}
