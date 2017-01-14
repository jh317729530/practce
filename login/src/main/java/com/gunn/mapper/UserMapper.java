package com.gunn.mapper;

import com.gunn.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Gunn on 2016/12/20.
 */
@Mapper
public interface UserMapper {
    public User selectUserByID(int id);
    public User selectUserByName(String user_name);

    //@Select("SELECT * FROM user_t WHERE user_name=#{user_name} AND password=#{password}")
    public User selectUserByUsernameAndPassword(@Param("user_name") String user_name, @Param("password") String password);
    public void addUser(User user);
    public List<User> listAllUsers();
}
