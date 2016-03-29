package cn.edu.zju.isee.cms.controller;

import cn.edu.zju.isee.cms.entity.CT;
import cn.edu.zju.isee.cms.entity.CTSlide;
import cn.edu.zju.isee.cms.mapper.CTMapper;
import cn.edu.zju.isee.cms.mapper.CTSlideMapper;
import cn.edu.zju.isee.cms.service.LungCTImgService;
import cn.edu.zju.isee.cms.service.LungImgSlideService;
import org.apache.velocity.tools.generic.DateTool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
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

    @RequestMapping("/ctDetails")
    public String ctDetails(@PathVariable int ctId, Model model) {
        List<CTSlide> slides = slideService.getCTSlides(ctId);
        model.addAttribute("slides", slides);
        return "ctDetails";
    }


}
