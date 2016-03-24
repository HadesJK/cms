package cn.edu.zju.isee.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jql on 2016/3/24.
 */
@Controller
public class CTController {

    @RequestMapping("/list")
    public String getList() {
        return "ctList";
    }
}
