package com.handsome.auction.bean;

import com.handsome.auction.util.DateUtil;
import com.wang.db2.*;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * by wangrongjun on 2017/7/12.
 */
@Entity(name = "auctionRecord")
public class Record {

    @com.wang.db2.Id
    @Id
    @GeneratedValue
    private int recordId;
    @Reference
    @ManyToOne
    private User user;//用户ID 外键列
    @Reference
    @ManyToOne
    private Auction auction;//拍卖品ID 外键列
    @Column(nullable = false)
    private Date auctionTime;
    @Column(columnDefinition = "decimal(9,2) not null")
    private Long auctionPrice;

    public Record() {
    }

    public Record(User user, Auction auction, String auctionTime, Long auctionPrice) {
        this.user = user;
        this.auction = auction;
        setAuctionTime(auctionTime);
        this.auctionPrice = auctionPrice;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int id) {
        this.recordId = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public Date getAuctionTime() {
        return auctionTime;
    }

    public void setAuctionTime(Date auctionTime) {
        this.auctionTime = auctionTime;
    }

    public void setAuctionTime(String auctionTime) {
        try {
            this.auctionTime = DateUtil.toDate(auctionTime);
        } catch (DateUtil.DateTextSyntaxErrorException e) {
            e.printStackTrace();
        }
    }

    public Long getAuctionPrice() {
        return auctionPrice;
    }

    public void setAuctionPrice(Long auctionPrice) {
        this.auctionPrice = auctionPrice;
    }
}
