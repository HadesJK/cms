package cn.edu.zju.isee.cms.interceptor;

import cn.edu.zju.isee.cms.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 508_1 on 2016/4/7.
 */
public class UserSecurityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
//        验证用户是否登陆
        Object obj = request.getSession().getAttribute("user");
        if (obj == null || !(obj instanceof User)) {
//            String a = (request.getLocalName()+":"+request.getLocalPort()+"/login");
//            String a = request.getContextPath()+"/login";
            response.sendRedirect(request.getContextPath()+"/signin");
            return false;
        }

//        response.sendRedirect(request.getContextPath()+"/signin");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
}
