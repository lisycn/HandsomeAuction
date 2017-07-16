package com.handsome.auction.service;

import com.handsome.auction.dao.base.DaoFactory;
import com.handsome.auction.dao.RecordDao;
import com.wang.db2.Where;

/**
 * by wangrongjun on 2017/7/14.
 */
public class RecordService {

    private RecordDao recordDao = DaoFactory.getRecordDao();

    public long getCount(int userId) {
//        return recordDao.queryCount(Where.build("user_userId", userId + ""));
        return recordDao.queryCount(Where.build("user", userId + ""));
    }

}
