package cn.edu.zju.isee.cms.mapper;

import cn.edu.zju.isee.cms.entity.CTSlide;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 508_1 on 2016/3/25.
 */
public interface CTSlideMapper {

    int insert(CTSlide ctSlide);
    List<Integer> selectAllIds();
    CTSlide selectById(int id);
    List<CTSlide> selectByCtId(int ctId);
    void updateById(CTSlide ctSlide);
    CTSlide selectByTwo(@Param("ctId") int ctId, @Param("serialNum") int serialNum);

}
