package com.gunn.interceptor;

import com.gunn.entity.PersistentLogins;
import com.gunn.entity.User;
import com.gunn.service.PersistentLoginsService;
import com.gunn.service.UserService;
import com.gunn.utils.CookieConstantTable;
import com.gunn.utils.CookieUtils;
import com.gunn.utils.EncryptionUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


public class UserInterceptor extends HandlerInterceptorAdapter {

    @Resource(name = "persistentLoginsServiceImpl")
    private PersistentLoginsService persistentLoginsService;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");

        if (user!=null){
            System.out.println("已经登录");
            return true;
        }else {
            Cookie cookie = CookieUtils.getCookie(request, CookieConstantTable.access_token);
            if (cookie!=null){
                String cookieValue = EncryptionUtil.base64Decode(cookie.getValue());
                String[] cValues = cookieValue.split("\\.");
                if (cValues.length == 2) {
                    String usernameByCookie=cValues[0];

                    String uuidByCookie = cValues[1];

                    PersistentLogins persistentLogins = persistentLoginsService.selectByUsernameAndSeries(usernameByCookie, uuidByCookie);

                    if (persistentLogins!=null){
                        String savedToken = persistentLogins.getToken();

                        Date savedValidtime = persistentLogins.getValidtime();
                        Date currentTime = new Date();

                        if (currentTime.before(savedValidtime)) {
                            User u = userService.selectUserByName(usernameByCookie);

                            if (u != null) {
                                Calendar calendar= Calendar.getInstance();
                                calendar.setTime(persistentLogins.getValidtime());

                                String timeString = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH)
                                        + "-" + calendar.get(Calendar.DAY_OF_MONTH) + "-"
                                        + calendar.get(Calendar.HOUR_OF_DAY) + "-" + calendar.get(Calendar.MINUTE);

                                String newToken = EncryptionUtil.sha256Hex(u.getUser_name() + "_" + u.getPassword() + "_" + timeString + "_" + CookieConstantTable.sslt);

                                if (savedToken.equals(newToken)) {
                                    String uuidNewString = UUID.randomUUID().toString();
                                    String newCookieValue = EncryptionUtil.base64Encode(u.getUser_name() + "." + uuidNewString);

                                    CookieUtils.editCookie(request, response, CookieConstantTable.access_token, newCookieValue, null);

                                    persistentLogins.setSeries(uuidNewString);

                                    persistentLoginsService.updateByPrimaryKeySelective(persistentLogins);

                                    session.setAttribute("user", u);
                                    return true;
                                } else {
                                    CookieUtils.delCookie(response,cookie);
                                    persistentLoginsService.deleteByPrimaryKey(persistentLogins.getId());
                                }

                            }
                        }
                    }
                }

            }
        }return  false;
    }
}
