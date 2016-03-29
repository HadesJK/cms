package cn.edu.zju.isee.cms.controller;

import cn.edu.zju.isee.cms.GlobalConstant;
import cn.edu.zju.isee.cms.service.LungImgSlideService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;

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

    @RequestMapping(value = "/list/Download", method = RequestMethod.GET)
    public void handleFileDownload(HttpServletResponse res, @RequestParam("id") String id) {
        String filename = "SL0058.zip";
        try {
//            String fn = "/Test.xls";
//            URL url = getClass().getResource(fn);
//            File f = new File(url.toURI());
            System.out.println(id);
            File f = new File(GlobalConstant.ZIP_ZHEYI_LUNG + "/1/" + filename);
            System.out.println("Loading file "+ filename +"("+f.getAbsolutePath()+")");
            if (f.exists()) {
                res.setContentType("application/zip");
                res.setContentLength(new Long(f.length()).intValue());
                res.setHeader("Content-Disposition", "attachment; filename="+f.getName());
                FileCopyUtils.copy(new FileInputStream(f), res.getOutputStream());
            } else {
                System.out.println("File"+ filename +"("+f.getAbsolutePath()+") does not exist");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
