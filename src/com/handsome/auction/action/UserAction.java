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
    private String passwords;// ��֤�룬��Ҫ��session��numrand���ԱȽ�

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
            ActionContext.getContext().put("msg", "�������û���������");
            return ERROR;
        }
        if (TextUtil.isEmpty(passwords)) {
            ActionContext.getContext().put("msg", "��������֤��");
            return ERROR;
        }
        if (!passwords.equals(ActionContext.getContext().getSession().get("numrand"))) {
            ActionContext.getContext().put("msg", "��֤�����");
            return ERROR;
        }

        User user = service.login(username, password);
        if (user != null) {
            ActionContext.getContext().getSession().put("user", user);
            return user.getRoot() == 1 ? "manager" : "user";
        } else {
            ActionContext.getContext().put("msg", "�û������������");
            return ERROR;
        }
    }

    public String register() throws Exception {
        if (TextUtil.isEmpty(user.getUsername(), user.getPassword(), user.getIdNumber(),
                user.getPhone(), user.getAddress(), user.getPostNumber())) {
            ActionContext.getContext().put("msg", "�������û���������");
            return ERROR;
        }
        if (TextUtil.isEmpty(passwords)) {
            ActionContext.getContext().put("msg", "��������֤��");
            return ERROR;
        }
        if (!passwords.equals(ActionContext.getContext().getSession().get("numrand"))) {
            ActionContext.getContext().put("msg", "��֤�����");
            return ERROR;
        }

        if (service.register(user)) {
            ActionContext context = ActionContext.getContext();
            context.put("username", user.getUsername());
            context.put("password", user.getPassword());
            return SUCCESS;
        } else {
            ActionContext.getContext().put("msg", "�û����Ѵ���");
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
