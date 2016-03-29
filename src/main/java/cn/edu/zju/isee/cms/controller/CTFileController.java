package cn.edu.zju.isee.cms.controller;

import cn.edu.zju.isee.cms.service.LungImgSlideService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Created by jql on 2016/3/24.
 */
@Controller
public class CTFileController {

    @Resource
    private LungImgSlideService service;

    @RequestMapping("upload")
    public String fileUpload() {
        return "upload/upload";
    }

    @RequestMapping(value = "filesUpload", produces = "text/html;")
    @ResponseBody
    public String upload(@RequestParam("files") MultipartFile[] files, Model model) {
        System.out.println(files.length);
        if (files != null && files.length > 0) {
            service.saveFiles(files);
        }
        return "husky";
    }
}
