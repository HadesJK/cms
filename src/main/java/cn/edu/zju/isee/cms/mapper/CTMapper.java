package cn.edu.zju.isee.cms.mapper;

import cn.edu.zju.isee.cms.entity.CT;

import java.util.List;

/**
 * Created by jql on 2016/3/24.
 */
public interface CTMapper {

    int insert(CT ct);

    List<Integer> selectAllIds();

    CT selectById(int id);

    void updateById(CT ct);

    List<CT> selectAll();
}
