import com.learn.ssm.chapter9.bean.JuiceMaker2;
import org.junit.Test;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-cfg.xml");
//        JuiceMaker2 juiceMaker2 = (JuiceMaker2) context.getBean("juiceMaker2");
//        System.out.println(juiceMaker2.makeJuice());
        JuiceMaker2 maker2 = (JuiceMaker2) context.getBean("maker2");
        System.out.println(maker2.makeJuice());
        context.close();
    }


    public void myTest() throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        String aa = "123456";
        byte [] md5bytes =  digest.digest(aa.getBytes());
        StringBuffer stringBuffer = new StringBuffer();
        for(int i=0;i<md5bytes.length;i++){
            int val = (int)md5bytes[i]&0xff;
            if(val<16){
                stringBuffer.append("0");
            }
            stringBuffer.append(Integer.toHexString(val));
        }
        System.out.println(stringBuffer.toString());
    }
}
