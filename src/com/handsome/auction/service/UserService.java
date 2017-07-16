package com.handsome.auction.service;

import com.handsome.auction.bean.User;
import com.handsome.auction.dao.base.DaoFactory;
import com.handsome.auction.dao.UserDao;
import com.wang.db2.Where;

import java.util.List;

/**
 * by wangrongjun on 2017/7/13.
 */
public class UserService {

    private UserDao userDao = DaoFactory.getUserDao();

    public User login(String username, String password) {
        Where where = new Where().equal("username", username).equal("password", password);
        List<User> userList = userDao.query(where);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    public boolean register(User user) {
        if (userDao.queryCount(Where.build("username", user.getUsername())) == 0) {
            userDao.insert(user);
            return true;
        }
        return false;
    }

}
