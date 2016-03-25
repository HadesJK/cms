package cn.edu.zju.isee.cms;

import cn.edu.zju.isee.cms.mapper.CTMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by jql on 2016/3/25.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("springmvc/springMVC-servlet.xml");
        if (context == null) {
            System.out.println("impossible.");
        } else {
            System.out.println(context.containsBean("cTMapper"));
            context.getBean(CTMapper.class);
        }
    }
}
