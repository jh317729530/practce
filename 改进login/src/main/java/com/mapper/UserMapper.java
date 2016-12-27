package com.mapper;

import com.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Gunn on 2016/12/20.
 */
public interface UserMapper {
    public User selectUserByID(int id);
    public User selectUserByName(String user_name);

    public User selectUserByUsernameAndPassword(@Param("user_name") String user_name,@Param("password") String password);
    public void addUser(User user);
    public List<User> listAllUsers();
}
