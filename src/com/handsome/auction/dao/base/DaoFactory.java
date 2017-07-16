package com.handsome.auction.dao.base;

import com.handsome.auction.dao.AuctionDao;
import com.handsome.auction.dao.RecordDao;
import com.handsome.auction.dao.UserDao;
import com.handsome.auction.dao.impl.AuctionDaoImpl;
import com.handsome.auction.dao.impl.RecordDaoImpl;
import com.handsome.auction.dao.impl.UserDaoImpl;

/**
 * by wangrongjun on 2017/7/12.
 */
public class DaoFactory {

    public static UserDao getUserDao() {
        return new UserDaoImpl();
    }

    public static AuctionDao getAuctionDao() {
        return new AuctionDaoImpl();
    }

    public static RecordDao getRecordDao() {
        return new RecordDaoImpl();
    }

}
