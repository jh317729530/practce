package com.service;

import com.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Gunn on 2016/12/20.
 */
public interface UserService {
    public User selectUserByID(int id);
    public User selectUserByName(String user_name);
    public int addUser(User user);
    public List<User> listAllUsers();

    public User login(User user, HttpServletResponse response);


}
