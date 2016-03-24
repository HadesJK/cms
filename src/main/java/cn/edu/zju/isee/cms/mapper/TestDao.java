package cn.edu.zju.isee.cms.mapper;

import cn.edu.zju.isee.cms.entity.Test;
import org.springframework.stereotype.Component;

/**
 * Created by jql on 2016/3/23.
 */
@Component
public interface TestDao {
    Test getById(int id);
}
