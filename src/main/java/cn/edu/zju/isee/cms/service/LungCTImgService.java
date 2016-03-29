package cn.edu.zju.isee.cms.service;

import cn.edu.zju.isee.cms.entity.CT;
import cn.edu.zju.isee.cms.entity.CTSlide;
import cn.edu.zju.isee.cms.mapper.CTMapper;
import cn.edu.zju.isee.cms.mapper.CTSlideMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jql on 2016/3/28.
 */
@Service
public class LungCTImgService {

    @Resource
    private CTMapper ctMapper;

    public List<CT> getCTList() {
        return ctMapper.selectAll();
    }

}
