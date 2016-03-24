package cn.edu.zju.isee.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jql on 2016/3/24.
 */
@Controller
public class CTFileController {

    @RequestMapping("upload")
     public String fileUpload() {
        return "upload/upload";
    }

    @RequestMapping("filesUpload")
    public String upload(HttpServletRequest request, Model model) {
        String fileName = request.getParameter("fileName");
        model.addAttribute("fileName", fileName);
        return "upload/uploadSuccess";
    }
}
