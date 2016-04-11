package cn.edu.zju.isee.cms.controller.model;

import cn.edu.zju.isee.cms.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 508_1 on 2016/4/9.
 */
@Controller
public class WelcomeController {
    @RequestMapping("/welcome")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("user/welcome");
        mv.addObject("userName",((User) request.getSession().getAttribute("user")).getUserName());
        return mv;
    }
}
