import com.ant.bean.Student;
import com.ant.bean.Teacher;
import com.ant.bean.myDiySpringIOC.BeanDefined;
import com.ant.bean.myDiySpringIOC.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class TestMain {

    public static void main(String[] args) throws Exception {
        testMySpring();
    }

    public static void testSpring(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_config.xml");
        Teacher teacher = applicationContext.getBean(Teacher.class);
        Student student = applicationContext.getBean(Student.class);
        System.out.println(teacher);
        System.out.println(student);
    }

    public static void testMySpring() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        BeanDefined beanDefined = new BeanDefined();
        beanDefined.setBeanId("teacher");
        beanDefined.setClasPath("com.ant.bean.Teacher");
        List<BeanDefined> beanDefinedList = new ArrayList<BeanDefined>();
        beanDefinedList.add(beanDefined);
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.setBeanDefinedList(beanDefinedList);
        Teacher teacher = (Teacher)beanFactory.getBean("teacher");
        System.out.println(teacher);
    }
}