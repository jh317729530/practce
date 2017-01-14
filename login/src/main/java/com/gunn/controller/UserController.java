package com.gunn.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.gunn.service.UserService;
import com.gunn.entity.User;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;



@RestController
public class UserController {

    @Resource(name = "userServiceImpl")
    private UserService userService;

    public void jsonObjectPut(JSONObject jsonObject, boolean su, String err, String mess, User user){
        jsonObject.put("success",su);
        jsonObject.put("error",err);
        jsonObject.put("msg",mess);
        jsonObject.put("data",user);
    }

    public void put(JSONObject jsonObject, boolean su, String err, String mess, List<User> users ){
        jsonObject.put("success",su);
        jsonObject.put("error",err);
        jsonObject.put("msg",mess);
        jsonObject.put("users",users);
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public
    JSONObject login(@RequestParam("user_name")String user_name, @RequestParam("password")String password, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes){
        JSONObject jsonObject = new JSONObject();
        HttpSession session=request.getSession();
        User user=new User();
        user.setUser_name(user_name);
        user.setPassword(password);

        User result = userService.login(user, response);
        if (result != null) {
            session.setAttribute("user",result);
            jsonObjectPut(jsonObject,true,"","登录成功",user);
            return jsonObject;
        }else {
            jsonObjectPut(jsonObject,false,"账号密码错误","登录失败",result);
            return  jsonObject;
        }
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST,consumes = "application/json")
    public @ResponseBody
    JSONObject register(@RequestBody User user){
        JSONObject jsonObject = new JSONObject();
        User result=userService.selectUserByName(user.getUser_name());
        if (result==null){
            userService.addUser(user);
            jsonObjectPut(jsonObject,true,"","注册用户信息成功",user);
            return jsonObject;
        }else {
            jsonObjectPut(jsonObject,false,"用户名已存在","注册用户信息失败",user);
            return jsonObject;
        }
    }

    @RequestMapping(value = "/listusers",method = RequestMethod.GET,consumes = "application/json")
    public @ResponseBody
    JSONObject listUser(HttpServletRequest req, HttpServletResponse resp){
        JSONObject jsonObject = new JSONObject();
        List<User> users=userService.listAllUsers();
        put(jsonObject, true, "", "查询用户信息成功", users);
        return jsonObject;
    }
}
