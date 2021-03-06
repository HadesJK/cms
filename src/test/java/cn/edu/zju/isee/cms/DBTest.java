package cn.edu.zju.isee.cms;

import cn.edu.zju.isee.cms.entity.CT;
import cn.edu.zju.isee.cms.entity.CTSlide;
import cn.edu.zju.isee.cms.entity.User;
import cn.edu.zju.isee.cms.mapper.CTMapper;
import cn.edu.zju.isee.cms.mapper.CTSlideMapper;
import cn.edu.zju.isee.cms.mapper.UserMapper;
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
        ct.setDoctorName("test");
        ct.setHospital("test");
//        ct.setId(124);
        ct.setItem("test");
        ct.setZipDir("test");
        ct.setJpgDir("test");
        ct.setZipName("test");
        ct.setPatientName("test");
        ct.setSlideNum(123);
        CTMapper ctMapper = applicationContext.getBean(CTMapper.class);
        System.out.println(ctMapper.insert(ct));
        System.out.println(ct.getId());
    }

    @Test
    public void testSelectById(){
        CTMapper ctMapper = applicationContext.getBean(CTMapper.class);
        CT ct = ctMapper.selectById(146);
        System.out.println(ct.getBuildTime());
    }

    @Test
    public void testSelectAllIds(){
        CTMapper ctMapper = applicationContext.getBean(CTMapper.class);
        List<Integer> idList = ctMapper.selectAllIds();
        for (Integer id : idList ){
            System.out.println(id);
        }
    }

    @Test//updata语句有小Bug
    public void testUpdateById(){
        CT ct = new CT();
//        ct.setBaseDir("test");
        ct.setBuildTime(new Date(86400000));
        ct.setDescr("test001");
        ct.setDoctorName("test002");
        ct.setHospital("test003");
        ct.setId(147);
        ct.setItem("test004");
        ct.setZipDir("test006");
        ct.setJpgDir("test007");
        ct.setZipName("test008");
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

    @Test
    public void testCTSlideGetAllIds(){
        CTSlideMapper ctSlideMapper = applicationContext.getBean(CTSlideMapper.class);
        List<Integer> idList = ctSlideMapper.selectAllIds();
        for(Integer id : idList){
            System.out.println(id);
        }
    }

//    @Test
//    public void testCTSlideSelectById(){
//        CTSlideMapper ctSlideMapper = applicationContext.getBean(CTSlideMapper.class);
//        List<CTSlide> ctSlideList = ctSlideMapper.selectById(100);
//        for(CTSlide ctSlide : ctSlideList){
//            System.out.println(ctSlide.getSlideName());
//        }
//    }

    @Test
    public void testCTSlideSelectByCtId(){
        CTSlideMapper ctSlideMapper = applicationContext.getBean(CTSlideMapper.class);
        List<CTSlide> ctSlideList = ctSlideMapper.selectByCtId(136);
        for(CTSlide ctSlide : ctSlideList){
            System.out.println(ctSlide.getSlideName());
        }
    }

    @Test//updata语句有小Bug
    public void testCTSlideUpdateById(){
        CTSlide ctSlide = new CTSlide();
        ctSlide.setId(93);
//        ctSlide.setSlideName("test");
//        ctSlide.setSerialNum(123);
        ctSlide.setCtId(136);
        CTSlideMapper ctSlideMapper = applicationContext.getBean(CTSlideMapper.class);
        ctSlideMapper.updateById(ctSlide);
    }

    @Test
    public void testUserSelectById(){
        UserMapper userMapper = applicationContext.getBean(UserMapper.class);
        User user = userMapper.selectById(1);
        System.out.println(user.getId() + user.getPassWord() + user.getUserName());
    }

    @Test
    public void testUserInsert(){
        User user = new User();
        user.setCheckData(true);
        user.setDownload(true);
        user.setPassWord("111111");
        user.setStatus(2);
        user.setUpload(true);
        user.setUserName("Bob");
        UserMapper userMapper = applicationContext.getBean(UserMapper.class);
        System.out.println(userMapper.insert(user));
        System.out.println(user.getId());
    }
}
