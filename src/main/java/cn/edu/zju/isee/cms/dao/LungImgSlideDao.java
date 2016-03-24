package cn.edu.zju.isee.cms.dao;

import cn.edu.zju.isee.cms.entity.LungImgSlide;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jql on 2016/3/24.
 */
@Component
public interface LungImgSlideDao {
    void insert(LungImgSlide ct);
    void batchInsert(List<LungImgSlide> list);
    LungImgSlide selectBySlideId(int slideId);
    List<LungImgSlide> selectById(int id);

}
