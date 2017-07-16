package com.handsome.auction.listener;

import com.handsome.auction.dao.base.BaseDao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * by wangrongjun on 2017/7/13.
 */
public class HibernateListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        BaseDao.closeSessionFactory();
    }
}
