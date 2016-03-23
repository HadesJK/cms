package com.jql.springmvc.controller;

import cn.edu.zju.isee.cms.dao.TestDao;
import cn.edu.zju.isee.cms.entity.Test;
import com.jql.springmvc.pojo.Book;
import com.jql.springmvc.pojo.User;
import jdk.nashorn.internal.runtime.ECMAException;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * UserController
 *
 * @author 一饭
 * @date 15/6/29 下午9:25
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private ConcurrentMap<String, User> users = new ConcurrentHashMap<String, User>(8);

    @Resource
    private TestDao dao;

    public UserController() {
        users.putIfAbsent("jql", new User("jql", "lqj"));
        users.putIfAbsent("syx", new User("syx", "xys"));
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String list(Model model) {
        Test entity = dao.getById(1);
        User x = new User("x", "ql");
        List<Book> books = x.getBooks();
        for (int i =0; i < 6; i ++) {
            books.add(new Book("hades" + i, i * 0.3));
        }
        model.addAttribute("users", users);
        model.addAttribute("msg", "Hello jql.");
        model.addAttribute("x", x);
        System.out.println("user/users");
        return "user/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "user/add";
    }

    //文件上传
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(String username, String password, MultipartFile attach, HttpServletRequest request) throws IOException {
        System.out.println(username);
        System.out.println(password);
        System.out.println(attach.getName() + ", " + attach.getOriginalFilename() + ", " + attach.getContentType());
        String realPath = request.getSession().getServletContext().getRealPath("/resources/user/uploads");
        System.out.println(realPath);
        File file = new File(realPath + "/" + attach.getOriginalFilename());
        FileUtils.copyInputStreamToFile(attach.getInputStream(), file);
        users.put(username, new User(username, password));
        return "redirect:/user/users";
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String show(@PathVariable String username, Model model) {
        System.out.println("query user name:" + username);
        model.addAttribute("user", users.get(username));
        return "user/details";
    }

    @RequestMapping(value = "/{username}/update", method = RequestMethod.GET)
    public String update(@PathVariable String username, Model model) {
        model.addAttribute("user", users.get(username));
        return "user/update";
    }

    @RequestMapping(value = "/{username}/update", method = RequestMethod.POST)
    public String update(String username, String password) {
        users.put(username, new User(username, password));
        return "redirect:/user/users";
    }

    //返回json
    @RequestMapping(value = "/{username}", method = RequestMethod.GET, params = "json")
    @ResponseBody
    public User showUser(@PathVariable String username) {
        System.out.println("return json data.");
        return users.get(username);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET, params = "test")
    public String showUser(@PathVariable String username, Model model) {
        System.out.println(username);
        model.addAttribute("message","大坏蛋");
        return "user/test";
    }
}
