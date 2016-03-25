package cn.edu.zju.isee.cms.mapper;

import cn.edu.zju.isee.cms.entity.CT;
import org.springframework.stereotype.Component;

/**
 * Created by jql on 2016/3/24.
 */
@Component
public interface CTMapper {
    int insert(CT ct);
}
