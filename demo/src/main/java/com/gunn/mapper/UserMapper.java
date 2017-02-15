package com.gunn.mapper;

import com.gunn.entity.User;

/**
 * Created by Gunn on 2017/2/15.
 */
public interface UserMapper {

    User selectUserById(Integer id);
}
