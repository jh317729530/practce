import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Gunn on 2016/12/20.
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");

        String[] names=ac.getBeanDefinitionNames();
        for (String name:names
             ) {
            System.out.println(name);
        }
    }
}
