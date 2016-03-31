package cn.edu.zju.isee.cms.controller;

import cn.edu.zju.isee.cms.GlobalConstant;
import cn.edu.zju.isee.cms.entity.CT;
import cn.edu.zju.isee.cms.service.LungCTImgService;
import cn.edu.zju.isee.cms.service.LungImgSlideService;
import org.springframework.stereotype.Controller;
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
import java.io.IOException;

/**
 * Created by jql on 2016/3/24.
 */
@Controller
public class CTFileController {

    @Resource
    private LungImgSlideService imgSlideService;

    @Resource
    private LungCTImgService ctImgService;

    @RequestMapping("upload")
    public String fileUpload() {
        return "upload/upload";
    }

    @RequestMapping("uploadMultiple")
    public String fileUpload2() { return "upload/uploadMultiple";}

    @RequestMapping(value = "filesUpload", produces = "text/html;")
    @ResponseBody
    public String upload(@RequestParam("files") MultipartFile[] files) {
        if (files != null && files.length > 0) {
            imgSlideService.saveFiles(files);
        }
        return "success";
    }

    @RequestMapping(value = "/list/download", method = RequestMethod.GET)
    public void handleFileDownload(HttpServletResponse res, @RequestParam("id") int id) {
        CT ct = ctImgService.getCT(id);
        String path = GlobalConstant.ZIP_ZHEYI_LUNG + ct.getZipDir() + ct.getZipName();
        try {
            File f = new File(path);
            System.out.println("Loading file "+ path +"("+f.getAbsolutePath()+")");
            if (f.exists()) {
                res.setContentType("application/zip");
                res.setContentLength(new Long(f.length()).intValue());
                res.setHeader("Content-Disposition", "attachment; filename="+f.getName());
                FileCopyUtils.copy(new FileInputStream(f), res.getOutputStream());
            } else {
                throw new IOException("File"+ path +"("+f.getAbsolutePath()+") does not exist");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
