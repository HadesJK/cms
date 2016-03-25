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
//    @RequestMapping(method = RequestMethod.POST, value = "filesUpload")
//    public void upload(HttpServletRequest request) {
//        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//        Map<String, MultipartFile> fileMap = multiRequest.getFileMap();
//        System.out.println(fileMap.size());
//
//    }
}
