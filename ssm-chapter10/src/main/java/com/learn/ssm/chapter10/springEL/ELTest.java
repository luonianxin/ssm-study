package com.learn.ssm.chapter10.springEL;

import com.learn.ssm.chapter10.annotation.pojo.Role;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.List;

public class ELTest {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'hello world'");
        String str = (String) exp.getValue();
        System.out.println(str);


        //通过ｓｐｒｉｎｇＥＬ表达式　调用普通方法
        exp = parser.parseExpression("'hello world'.charAt(6)");
        char  ch = (char) exp.getValue();
        System.out.println(ch);

        //调用ｇｅｔｔｅｒ方法
        exp = parser.parseExpression("'hello world'.bytes");
        byte [] bytes = (byte[]) exp.getValue();
        System.out.println(bytes);

        exp = parser.parseExpression("'hello world'.bytes.length");
        int length = (int) exp.getValue();
        System.out.println(length);

        exp = parser.parseExpression("new String('abc')");
        String abc = (String) exp.getValue();
        System.out.println(abc);

        //test getvalue from variable
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("role_name");
        role.setNote("note");
        exp = parser.parseExpression("note");
        String note = (String) exp.getValue(role);
        System.out.println(note);

        EvaluationContext ctx = new StandardEvaluationContext(role);
        parser.parseExpression("note").setValue(ctx,"new_note");
        note = parser.parseExpression("note").getValue(ctx,String.class);
        System.out.println(note);
        String roleName = parser.parseExpression("getRoleName()").getValue(ctx,String.class);
        System.out.println(roleName);
        List<String> list = new ArrayList<String>();
        list.add("value1");
        list.add("value2");
        list.add("value3");
        ctx.setVariable("list",list);
        parser.parseExpression("#list[1]").setValue(ctx,"value4");
        System.out.println(parser.parseExpression("#list[1]").getValue(ctx));
    }


}
