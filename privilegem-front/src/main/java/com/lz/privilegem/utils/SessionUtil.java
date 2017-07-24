package com.lz.privilegem.utils;

import com.lz.privilegem.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by lizhi on 2017/7/7.
 */
public class SessionUtil {

    /**
     * 系统登录用户名
     */
    public static final String SessionSystemLoginUserName = "SessionSystemLoginUserName";

    /**
     * 清空session
     */
    public static final void clearSession(HttpServletRequest request)
    {
        HttpSession session = request.getSession();

        session.removeAttribute(SessionUtil.SessionSystemLoginUserName);

        session.invalidate();//非必须，单点登出接收到服务器消息时，会自动销毁session
    }

    /**
     * 返回session中的用户对象
     * @param request
     * @return
     */
    public static final User getSessionUser(HttpServletRequest request)
    {
        return (User) request.getSession().getAttribute(SessionUtil.SessionSystemLoginUserName);
    }

    /**
     * 返回session中的用户对象
     * @param request
     * @return
     */
    public static final void setSessionUser(HttpServletRequest request,User user)
    {
        request.getSession().setAttribute(SessionUtil.SessionSystemLoginUserName,user);
    }
}
