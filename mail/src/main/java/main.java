import impl.CPU;
import mail.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by Gunn on 2017/2/22.
 */

public class main {

    private static MailUtil mailUtil;

    public static void main(String[] args) {

        ApplicationContext factory=new ClassPathXmlApplicationContext("spring.xml");
        mailUtil=(MailUtil) factory.getBean("mailUtil");


        CPU cpu=new CPU();
        if (cpu.getCpuRatio()>10){
            mailUtil.send("317729530@qq.com", "cpu占用率警告", "cpu可用剩余"+cpu.getCpuRatio());
        }
    }
}
