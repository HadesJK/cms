package cn.edu.zju.isee.cms.controller;

import cn.edu.zju.isee.cms.utils.file.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public String upload(@RequestParam("files") MultipartFile[] files, Model model) {
        if (files != null) {
            for (MultipartFile file : files) {
                String name = file.getOriginalFilename();
                try {
                    FileUtils.saveFile(file.getInputStream(), name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        model.addAttribute("fileName", "success");
        return "upload/uploadSuccess";
    }
}
