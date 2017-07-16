package com.handsome.auction.action;

import com.handsome.auction.bean.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wang.java_util.TextUtil;

/**
 * by wangrongjun on 2017/7/14.
 */
public class BaseAction extends ActionSupport {

    protected void checkParameter(String... parameterList) {
        if (parameterList == null || parameterList.length == 0) {
            return;
        }
        for (String parameter : parameterList) {
            if (TextUtil.isEmpty(parameter)) {
                addFieldError(parameter, parameter + " is null");
                continue;
            }
        }
    }

    protected User getUser() {
        return (User) ActionContext.getContext().getSession().get("user");
    }

    /**
     * 检查用户的权限
     *
     * @return manager：管理员  user：用户  login：未登录
     */
    protected String checkAdmin() {
        User user = getUser();
        if (user != null) {
            if (user.getRoot() == 1) {
                return "manager";
            } else {
                return "user";
            }
        } else {
            return LOGIN;
        }
    }

}
