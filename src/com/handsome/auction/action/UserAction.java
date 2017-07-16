package com.handsome.auction.action;

import com.handsome.auction.bean.User;
import com.handsome.auction.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.wang.java_util.TextUtil;
import org.apache.struts2.ServletActionContext;

/**
 * by wangrongjun on 2017/7/13.
 */
public class UserAction extends BaseAction implements ModelDriven<User> {

    private UserService service = new UserService();
    private User user = new User();
    private String passwords;// 验证码，需要和session的numrand属性比较

    @Override
    public User getModel() {
        return user;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public String login() throws Exception {
        String username = user.getUsername();
        String password = user.getPassword();
        if (TextUtil.isEmpty(username, password)) {
            ActionContext.getContext().put("msg", "请输入用户名和密码");
            return ERROR;
        }
        if (TextUtil.isEmpty(passwords)) {
            ActionContext.getContext().put("msg", "请输入验证码");
            return ERROR;
        }
        if (!passwords.equals(ActionContext.getContext().getSession().get("numrand"))) {
            ActionContext.getContext().put("msg", "验证码错误");
            return ERROR;
        }

        User user = service.login(username, password);
        if (user != null) {
            ActionContext.getContext().getSession().put("user", user);
            return user.getRoot() == 1 ? "manager" : "user";
        } else {
            ActionContext.getContext().put("msg", "用户名或密码错误");
            return ERROR;
        }
    }

    public String register() throws Exception {
        if (TextUtil.isEmpty(user.getUsername(), user.getPassword(), user.getIdNumber(),
                user.getPhone(), user.getAddress(), user.getPostNumber())) {
            ActionContext.getContext().put("msg", "请输入用户名和密码");
            return ERROR;
        }
        if (TextUtil.isEmpty(passwords)) {
            ActionContext.getContext().put("msg", "请输入验证码");
            return ERROR;
        }
        if (!passwords.equals(ActionContext.getContext().getSession().get("numrand"))) {
            ActionContext.getContext().put("msg", "验证码错误");
            return ERROR;
        }

        if (service.register(user)) {
            ActionContext context = ActionContext.getContext();
            context.put("username", user.getUsername());
            context.put("password", user.getPassword());
            return SUCCESS;
        } else {
            ActionContext.getContext().put("msg", "用户名已存在");
            return ERROR;
        }
    }

    public String logout() throws Exception {
        User user = (User) ActionContext.getContext().getSession().get("user");
        if (user != null) {
            ActionContext.getContext().put("username", user.getUsername());
        }
        ServletActionContext.getRequest().getSession().invalidate();
        return SUCCESS;
    }

}
