package cn.edu.zju.isee.cms.controller;

import cn.edu.zju.isee.cms.GlobalConstant;
import cn.edu.zju.isee.cms.controller.model.RecogModel;
import cn.edu.zju.isee.cms.entity.CT;
import cn.edu.zju.isee.cms.entity.CTSlide;
import cn.edu.zju.isee.cms.mapper.CTSlideMapper;
import cn.edu.zju.isee.cms.service.CTImageFileService;
import cn.edu.zju.isee.cms.service.LungCTImgService;
import cn.edu.zju.isee.cms.service.LungImgSlideService;
import org.apache.velocity.tools.generic.DateTool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by jql on 2016/3/24.
 */
@Controller
public class CTController {

    @Resource
    private CTSlideMapper ctSlideMapper;

    @Resource
    private LungCTImgService imgService;

    @Resource
    private LungImgSlideService slideService;

    @Resource
    private CTImageFileService imageFileService;

    @RequestMapping("/list")
    public String getList(Model model) {
        List<CT> ct = imgService.getCTList();
        model.addAttribute("ct", ct);
        model.addAttribute("date", new DateTool());
        return "ctlist/ctList";
    }

    @RequestMapping("/list/{ctId}")
    public String predictAll() {
        //TODO: @HadesJK
        return null;
    }


    @RequestMapping("/ctDetails/{ctId}")
    public String ctDetails(@PathVariable int ctId, Model model) {
        List<CTSlide> slides = slideService.getCTSlides(ctId);
        model.addAttribute("slides", slides);
        model.addAttribute("ctId", ctId);
        return "ctlist/slideList";
    }

    @RequestMapping("/autoRecog")
    public String autoRecog(HttpServletRequest request, @RequestParam("ctId") int ctId, @RequestParam("serialNum") int serialNum, Model model, HttpServletResponse response){

        model.addAttribute("slideId", serialNum);
        model.addAttribute("ctId", ctId);
        StringBuffer path = request.getRequestURL();
//        String realPath = path.substring(0,path.indexOf("d"));
        System.out.println(path);
        model.addAttribute("dicomUrl", path + "/beforeProcessing");

        CTSlide ctSlide = ctSlideMapper.selectByTwo(ctId, serialNum);
        System.out.println(ctId + " " + serialNum + " " + ctSlide.getSlideName());
        return "ctlist/autoRecog";
    }

    @RequestMapping("/autoRecog/beforeProcessing")
    public void beforeProcessing(HttpServletResponse response){

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream("E:/test/test.dcm"));
            outputStream = response.getOutputStream();
            int l = 0;
            byte[] buffer = new byte[4096];
            while((l = inputStream.read(buffer)) != -1){
                outputStream.write(buffer, 0, l);
            }
            outputStream.flush();
            inputStream.close();
            outputStream.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping("/predict/{ctId}")
    public String ctPredictAll(@PathVariable int ctId, Model model) {
        String msg;
        try {
            List<RecogModel> result = imgService.ctPredict(ctId);

//            List<RecogModel> result = new ArrayList<RecogModel>();
//            for(int i =1; i < 6; i++) {
//                RecogModel rModel = new RecogModel();
//                rModel.pic = "sorry @mch";
//                rModel.label = "test";
//                rModel.probably = "cloudy day";
//                rModel.serialNum = i;
//                result.add(rModel);
//            }
            model.addAttribute("preds", result);
            msg = "ctlist/predList";
        } catch (Exception e) {
            model.addAttribute("exception", e.getMessage());
            msg = "error";
            e.printStackTrace();
        }
        return msg;
    }

    @RequestMapping("/dicomView/{ctId}/{slideId}")
    public String ctDicomView(@PathVariable int ctId, @PathVariable int slideId, Model model, HttpServletRequest request){
        model.addAttribute("slideId", slideId);
        model.addAttribute("ctId", ctId);
        StringBuffer path = request.getRequestURL();
        String realPath = path.substring(0,path.indexOf("d"));
        model.addAttribute("dicomUrl", realPath + "dicomPath/" + ctId + "/" + slideId);
//        model.addAttribute("dicomUrl", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2413511449,3739465490&fm=116&gp=0.jpg");//GlobalConstant.JAVA_MATLAB_DIR + slideId + ".dcm");
        return "ctlist/dicomView";
    }

    @RequestMapping("/dicomPath/{ctId}/{slideId}")
    public void getDicom(@PathVariable int ctId, @PathVariable int slideId, HttpServletResponse response) {
        InputStream in = null;
        OutputStream o = null;
        try {
            in = new BufferedInputStream(imageFileService.getCtImage(slideId));
            o = response.getOutputStream();
            int l = 0;
            byte[] buffer = new byte[4096];
            while((l = in.read(buffer)) != -1){
                o.write(buffer,0,l);
            }
            o.flush();
            in.close();
            o.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (o != null) {
                try {
                    o.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
