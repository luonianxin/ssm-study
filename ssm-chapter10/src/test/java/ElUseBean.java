import com.learn.ssm.chapter10.el.pojo.ApplicationELConig;
import com.learn.ssm.chapter10.el.pojo.ELBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ElUseBean {
    public static void main(String[] args) throws ParseException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationELConig.class);

        ELBean ebean = context.getBean(ELBean.class);
        System.out.println(ebean.random);

        String strDate = "2020-01-11 11:50:30";
        Date a  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDate);
        Date b = new Date(a.getTime()+30000);
        System.out.println(a);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(b));
    }


}
