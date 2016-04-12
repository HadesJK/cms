package cn.edu.zju.isee.cms.mapper;

import cn.edu.zju.isee.cms.entity.User;

/**
 * Created by 508_1 on 2016/4/7.
 */
public interface UserMapper {

    int insert(User user);

    User selectById(int id);

    User selectByUserName(String userName);
}
