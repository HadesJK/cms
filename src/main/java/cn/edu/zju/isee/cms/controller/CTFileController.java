package cn.edu.zju.isee.cms.controller;

import cn.edu.zju.isee.cms.service.LungImgSlideService;
import cn.edu.zju.isee.cms.utils.file.FileUtils;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

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

    @RequestMapping("filesUpload")
    public void upload(@RequestParam("files") MultipartFile[] files, Model model) {
        if (files != null && files.length > 0) {
            service.saveFiles(files);
        }
    }
//    @RequestMapping(method = RequestMethod.POST, value = "filesUpload")
//    public void upload(HttpServletRequest request) {
//        DiskFileItemFactory dff = new DiskFileItemFactory();
//        ServletFileUpload upload = new ServletFileUpload(dff);
//        upload.setHeaderEncoding("utf-8");
//        List fileList = null;
//        try {
//            fileList = upload.parseRequest(request);
//        } catch (FileUploadException e) {
//            e.printStackTrace();
//        }
//        if (fileList != null && fileList.size() > 0) {
//            System.out.println(fileList.size());
//        }
//        System.out.println(fileList.size());
//    }
}
