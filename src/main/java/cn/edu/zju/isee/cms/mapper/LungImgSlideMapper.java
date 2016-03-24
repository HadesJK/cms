package cn.edu.zju.isee.cms.mapper;

import cn.edu.zju.isee.cms.entity.LungImgSlide;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jql on 2016/3/24.
 */
@Component
public interface LungImgSlideMapper {
    int insert(LungImgSlide slide);
    LungImgSlide selectBySlideId(int slideId);
    List<LungImgSlide> selectById(int id);

}
