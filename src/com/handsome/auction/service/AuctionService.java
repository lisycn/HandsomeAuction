package com.handsome.auction.service;

import com.handsome.auction.bean.Auction;
import com.handsome.auction.dao.AuctionDao;
import com.handsome.auction.dao.base.DaoFactory;
import com.wang.db2.Query;
import com.wang.db2.Where;
import com.wang.java_util.DateUtil;
import com.wang.java_util.TextUtil;

import java.util.Date;
import java.util.List;

/**
 * by wangrongjun on 2017/7/14.
 */
public class AuctionService {

    private AuctionDao auctionDao = DaoFactory.getAuctionDao();

    public List<Auction> getAuctionList(Auction where, int currentPage, int pageSize) {
        return auctionDao.query(Query.build(getWhere(where)).limit(currentPage * pageSize, pageSize));
    }

    public boolean update(Auction auction) {
        return auctionDao.update(auction);
    }

    public Auction getAuction(int auctionId) {
        return auctionDao.queryById(auctionId);
    }

    public long getCount(Auction where) {
        return auctionDao.queryCount(getWhere(where));
    }

    public boolean delete(int auctionId) {
        return auctionDao.deleteById(auctionId);
    }

    private Where getWhere(Auction auction) {
        Where where = new Where();
        if (auction != null) {
            String auctionName = auction.getAuctionName();
            String description = auction.getDescription();
            Date startTime = auction.getAuctionStartTime();
            Date endTime = auction.getAuctionEndTime();
            Long startPrice = auction.getStartPrice();

            if (!TextUtil.isEmpty(auctionName)) {
                where.like("auctionName", "%" + auctionName + "%");
            }
            if (!TextUtil.isEmpty(description)) {
                where.like("description", "%" + description + "%");
            }
            if (startTime != null) {
                where.moreEqual("auctionStartTime", DateUtil.toDateTimeText(startTime));
            }
            if (endTime != null) {
                where.lessEqual("auctionEndTime", DateUtil.toDateTimeText(endTime));
            }
            if (startPrice != null) {
                where.moreEqual("startPrice", startPrice + "");
            }
        }
        return where;
    }

}
