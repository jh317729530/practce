package com.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Gunn on 2016/12/20.
 */
public class CookieUtils {
    public static void addCookie(HttpServletResponse response, Cookie cookie){
        if (cookie!=null){
            response.addCookie(cookie);
        }
    }

    public static void  addCookie(HttpServletResponse response,String cookieName,String cookieValue,String domain,boolean httpOnly,int maxAge,String path,boolean secure){
        if (cookieName!=null && !cookieName.equals("")){
            if (cookieValue==null)
                cookieValue = "";
            Cookie newCookie=new Cookie(cookieName,cookieValue);
            if (domain!=null)
                newCookie.setDomain(domain);
            newCookie.setHttpOnly(httpOnly);

            if (maxAge>0)
                newCookie.setMaxAge(maxAge);
            if (path==null)
                newCookie.setPath("/");
            else
                newCookie.setPath(path);
            newCookie.setSecure(secure);
            addCookie(response,newCookie);
        }
    }

    public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue, String domain) {
        addCookie(response, cookieName, cookieValue, domain, true, CookieConstantTable.COOKIE_MAX_AGE, "/", false);
    }

    public static Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null || cookieName == null || cookieName.equals(""))
            return null;

        for (Cookie c : cookies) {
            if (c.getName().equals(cookieName))
                return (Cookie) c;
        }
        return null;
    }

    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie cookie = getCookie(request, cookieName);
        if (cookie == null)
            return null;
        else
            return cookie.getValue();
    }

    public static void delCookie(HttpServletResponse response, Cookie cookie) {
        if (cookie != null) {
            cookie.setPath("/");
            cookie.setMaxAge(0);
            cookie.setValue(null);

            response.addCookie(cookie);
        }
    }

    public static void delCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        Cookie c = getCookie(request, cookieName);
        if (c != null && c.getName().equals(cookieName)) {
            delCookie(response, c);
        }
    }

    public static void editCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
                                  String cookieValue,String domain) {
        Cookie c = getCookie(request, cookieName);
        if (c != null && cookieName != null && !cookieName.equals("") && c.getName().equals(cookieName)) {
            addCookie(response, cookieName, cookieValue, domain);
        }
    }
}
