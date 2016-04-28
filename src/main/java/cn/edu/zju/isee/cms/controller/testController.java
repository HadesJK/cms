package cn.edu.zju.isee.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by 508_1 on 2016/4/27.
 */

@Controller

public class testController {

    @RequestMapping("/test")
    private String test(){
        return "test/test";
    }

    @RequestMapping("/showImg")
    private void showImg(HttpServletRequest request, @RequestParam("img") String img ,HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setContentType("image/jpeg");
//        String fname = request.getParameter("filename");
//        String newpath = new String(fname.getBytes("ISO-8859-1"), "UTF-8");
        String absolutePath = "E:/" + img;
        FileInputStream fis = new FileInputStream(absolutePath);
        OutputStream os = response.getOutputStream();
        try
        {
            int count = 0;
            byte[] buffer = new byte[1024 * 1024];
            while ((count = fis.read(buffer)) != -1)
                os.write(buffer, 0, count);
            os.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (os != null)
                os.close();
            if (fis != null)
                fis.close();
        }
    }
}
