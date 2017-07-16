package com.handsome.auction.dao.base;

import com.wang.db2.BaseDao;
import com.wang.db2.Dao;

/**
 * by wangrongjun on 2017/7/14.
 */
public abstract class BaseDao2<T> extends BaseDao<T> implements Dao<T> {
    public BaseDao2() {
        super("root", "21436587", "handsome_auction", true, true);
    }
}
