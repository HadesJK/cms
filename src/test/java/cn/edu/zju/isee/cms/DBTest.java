package cn.edu.zju.isee.cms;

import cn.edu.zju.isee.cms.entity.CT;
import cn.edu.zju.isee.cms.entity.CTSlide;
import cn.edu.zju.isee.cms.mapper.CTMapper;
import cn.edu.zju.isee.cms.mapper.CTSlideMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
        CTMapper ctMapper = applicationContext.getBean(CTMapper.class);
        System.out.println(ctMapper.insert(ct));
        System.out.println(ct.getId());
    }

    @Test
    public void testSelectById(){
        CTMapper ctMapper = applicationContext.getBean(CTMapper.class);
        List<CT> ctList = ctMapper.selectById(123);
        for (CT ct : ctList){
            System.out.println(ct.getBuildTime());
        }
    }

    @Test
    public void testSelectAllIds(){
        CTMapper ctMapper = applicationContext.getBean(CTMapper.class);
        List<Integer> idList = ctMapper.selectAllIds();
        for (Integer id : idList ){
            System.out.println(id);
        }
    }

    @Test
    public void testUpdateById(){
        CT ct = new CT();
//        ct.setBaseDir("test");
        ct.setBuildTime(new Date(86400000));
        ct.setDescr("test001");
        ct.setDoctors("test002");
        ct.setHospital("test003");
        ct.setId(124);
        ct.setItem("test004");
        ct.setPatientName("test005");
        ct.setSlideNum(999);
        CTMapper ctMapper = applicationContext.getBean(CTMapper.class);
        ctMapper.updateById(ct);
    }
    @Test
    public void testCTSlideInsert(){
        CTSlide ctSlide = new CTSlide();
//        ctSlide.setId(123);
        ctSlide.setCtId(123);
        ctSlide.setSerialNum(123456);
        ctSlide.setSlideName("test");
        CTSlideMapper ctSlideMapper = applicationContext.getBean(CTSlideMapper.class);
        ctSlideMapper.insert(ctSlide);
        System.out.println(ctSlide.getId());
    }
}
