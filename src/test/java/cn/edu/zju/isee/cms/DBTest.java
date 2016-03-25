package cn.edu.zju.isee.cms;

import cn.edu.zju.isee.cms.entity.CT;
import cn.edu.zju.isee.cms.mapper.CTMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by 508_1 on 2016/3/25.
 */
public class DBTest {

    private ApplicationContext applicationContext;

    @Before
    public void setUp(){
        applicationContext = new ClassPathXmlApplicationContext("springMVC/springMVC-servlet.xml");
//        applicationContext = new FileSystemXmlApplicationContext("D:\\git_project\\cms\\src\\main\\webapp\\WEB-INF\\springMVC-servlet.xml");
    }

    @Test
    public void testInsert(){
        CT ct = new CT();
        ct.setBaseDir("test");
        ct.setBuildTime(new Date(0));
        ct.setDescr("test");
        ct.setDoctors("test");
        ct.setHospital("test");
//        ct.setId(124);
        ct.setItem("test");
        ct.setPatientName("test");
        ct.setSlideNum(123);
//        System.out.println(applicationContext.toString());
        CTMapper ctMapper = applicationContext.getBean(CTMapper.class);
        System.out.println(ctMapper.insert(ct));
        System.out.println(ct.getId());
    }
}
