
import com.learn.ssm.chapter10.annotation.config.ApplicationConfig;
import com.learn.ssm.chapter10.annotation.controller.RoleController;
import com.learn.ssm.chapter10.annotation.pojo.JuiceMaker2;
import com.learn.ssm.chapter10.annotation.pojo.PojoConfig;
import com.learn.ssm.chapter10.annotation.pojo.Role;
import com.learn.ssm.chapter10.annotation.pojo.Source;
import com.learn.ssm.chapter10.annotation.service.RoleService;
import com.learn.ssm.chapter10.annotation.service.RoleService2;
import com.learn.ssm.chapter10.annotation.service.impl.RoleServiceImpl;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        Role role = (Role) context.getBean("role1");
        RoleService roleService = context.getBean(RoleServiceImpl.class);
        roleService.printRoleInfo(role);
    }

    @Test
    public void TestAnnotation() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        Role role = (Role) context.getBean("role1");
        RoleService roleService = context.getBean(RoleServiceImpl.class);
        roleService.printRoleInfo(role);
        context.close();
    }


    @Test
    public void TestAutoWireAnnotation(){
        //2020-09-03 22:30:58

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        RoleController controller = context.getBean("controller",RoleController.class);
        JuiceMaker2 maker2 = (JuiceMaker2) context.getBean("juiceMaker2");
        maker2.makeJuice();
        context.close();
    }

    @Test
    public void myTest(){
        Source source = new Source();
        source.setSize("大杯");
        source.setSugar("少量糖");
        source.setFruit("香栗");
        JuiceMaker2 maker2 = new JuiceMaker2();
        maker2.setSource(source);


        Source source2 = maker2.getSource();
        source2.setSize("小杯");
        System.out.println(maker2.getSource().getSize());
    }

}

