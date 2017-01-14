package com.gunn.service.impl;

import com.gunn.entity.PersistentLogins;
import com.gunn.entity.User;
import com.gunn.mapper.UserMapper;
import com.gunn.service.UserService;
import com.gunn.utils.CookieConstantTable;
import com.gunn.utils.CookieUtils;
import com.gunn.utils.EncryptionUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Gunn on 2016/12/20.
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Resource(name ="userMapper" )
    private UserMapper userMapper;

    @Resource(name ="persistentLoginsServiceImpl")
    private PersistentLoginServiceImpl persistentLoginServiceImpl;

    public User selectUserByID(int id) {
        return userMapper.selectUserByID(id);
    }

    public User selectUserByName(String user_name) {
        return userMapper.selectUserByName(user_name);
    }

    public int addUser(User user) {
        userMapper.addUser(user);
        return user.getId();
    }

    public List<User> listAllUsers() {
        return userMapper.listAllUsers();
    }

    public User login(User user, HttpServletResponse response) {
        User result=new User();
        if (!StringUtils.isEmpty(user.getUser_name())&&!StringUtils.isEmpty(user.getPassword())){
            result=userMapper.selectUserByUsernameAndPassword(user.getUser_name(),user.getPassword());
            if (result!=null){
                Calendar calendar= Calendar.getInstance();
                calendar.add(Calendar.DATE,5);
                Date validTime=calendar.getTime();
                String timeString = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-"
                        + calendar.get(Calendar.DAY_OF_MONTH) + "-" + calendar.get(Calendar.HOUR_OF_DAY) + "-"
                        + calendar.get(Calendar.MINUTE);

                String userInfoBySha256 = EncryptionUtil.sha256Hex(result.getUser_name() + "_" + result.getPassword() + "_" + timeString + "_" + CookieConstantTable.sslt);
                String uuidString= UUID.randomUUID().toString();
                String cookieValue = EncryptionUtil.base64Encode(result.getUser_name() + "." + uuidString);

                PersistentLogins persistentLogins = persistentLoginServiceImpl.selectByUsername(result.getUser_name());
                if (persistentLogins==null){
                    persistentLogins=new PersistentLogins();
                    persistentLogins.setUsername(result.getUser_name());
                    persistentLogins.setSeries(uuidString);
                    persistentLogins.setToken(userInfoBySha256);
                    persistentLogins.setValidtime(validTime);
                    persistentLoginServiceImpl.insertSelective(persistentLogins);
                }else {
                    persistentLogins.setSeries(uuidString);
                    persistentLogins.setToken(userInfoBySha256);
                    persistentLogins.setValidtime(validTime);
                    persistentLoginServiceImpl.updateByPrimaryKeySelective(persistentLogins);
                }

                CookieUtils.addCookie(response, CookieConstantTable.access_token,cookieValue,null);
            }
        }
        return result;
    }


}
