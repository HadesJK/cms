package cn.edu.zju.isee.cms.controller;

import cn.edu.zju.isee.cms.entity.User;
import cn.edu.zju.isee.cms.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by 508_1 on 2016/4/7.
 */
@Controller
public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = { "", "/index", "/signin"}, method = RequestMethod.GET)
    public ModelAndView signInPage(HttpServletRequest request) {
        if(request.getSession().getAttribute("user") instanceof User) {
            ModelAndView mv = new ModelAndView();
//            mv.addObject("userID",((ReferenceUser) request.getSession().getAttribute("user")).getId()+"");
            mv.setView(new RedirectView("welcome", false));
            return mv;
        }
        else return new ModelAndView("user/signin");
//        return "user/signin";
    }

    @RequestMapping(value = "/signin",method = RequestMethod.POST)
    public ModelAndView signIn(HttpServletRequest request, @RequestParam("Username") String userName, @RequestParam("Password") String passWord, HttpServletResponse response) throws IOException{
        System.out.println(userName + "  " + passWord);
        if(userMapper.selectByUserName(userName)==null){
            //throw new ServiceException("用户名不存在");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<script>alert('用户名不存在！');</script>");
            return new ModelAndView("user/signin");
        }
        else if(!(userMapper.selectByUserName(userName).getPassWord().equals(passWord))){
            //throw new ServiceException("密码输入错误");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<script>alert('密码输入错误！');</script>");
            return new ModelAndView("user/signin");
        }
        else {
            HttpSession session=request.getSession();
            session.setAttribute("user",userMapper.selectByUserName(userName));
            ModelAndView mv = new ModelAndView();
//            mv.addObject("userID",((ReferenceUser)session.getAttribute("user")).getId()+"");
            mv.setView(new RedirectView("welcome", false));
            return mv;
        }
    }
}
