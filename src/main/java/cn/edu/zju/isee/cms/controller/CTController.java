package cn.edu.zju.isee.cms.controller;

import cn.edu.zju.isee.cms.GlobalConstant;
import cn.edu.zju.isee.cms.controller.model.RecogModel;
import cn.edu.zju.isee.cms.entity.CT;
import cn.edu.zju.isee.cms.entity.CTSlide;
import cn.edu.zju.isee.cms.service.LungCTImgService;
import cn.edu.zju.isee.cms.service.LungImgSlideService;
import org.apache.velocity.tools.generic.DateTool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by jql on 2016/3/24.
 */
@Controller
public class CTController {

    @Resource
    private LungCTImgService imgService;

    @Resource
    private LungImgSlideService slideService;

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
        return "ctlist/slideList";
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

    @RequestMapping("/dicomView/{slideId}")
    public String ctDicomView(@PathVariable int slideId, Model model, HttpServletRequest request){
        model.addAttribute("dicomName", slideId + ".dcm");
        StringBuffer path = request.getRequestURL();
        String realPath = path.substring(0,path.indexOf("d"));
        model.addAttribute("dicomUrl", realPath + "dicomPath/" + slideId);
//        model.addAttribute("dicomUrl", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2413511449,3739465490&fm=116&gp=0.jpg");//GlobalConstant.JAVA_MATLAB_DIR + slideId + ".dcm");
        return "ctlist/dicomView";
    }

    @RequestMapping("/dicomPath/{dicomId}")
    public void getDicom(@PathVariable int dicomId, HttpServletResponse response) {
        String fileName = GlobalConstant.JAVA_MATLAB_DIR + dicomId + ".dcm";
        File file = new File(fileName);
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            OutputStream o = response.getOutputStream();
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
        }

        return;
    }

}
