package com.handsome.auction.bean;

import com.handsome.auction.util.DateUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * by wangrongjun on 2017/7/12.
 */
@Entity(name = "auction")
public class Auction {

    @com.wang.db2.Id
    @Id
    @GeneratedValue
    private int auctionId;
    @Column(length = 50, nullable = false)
    private String auctionName;//商品名称
    @Column(columnDefinition = "decimal(9,2) not null")
    private Long minPrice;//加价的最小金额单位
    @Column(columnDefinition = "decimal(9,2) not null")
    private Long startPrice;//起拍价
    @Column(nullable = false)
    private Date auctionStartTime;//开始时间
    @Column(nullable = false)
    private Date auctionEndTime;//结束时间
    @Column(length = 50, nullable = false)
    private String image;//商品的图片的路径
    @Column(length = 20, nullable = false)
    private String auctionPicType;//商品的图片类型 jpg,png,gif
    @Column(length = 500, nullable = false)
    private String description;//商品的描述

    public Auction() {
    }

    public Auction(String auctionName, Long minPrice, Long startPrice, String auctionStartTime,
                   String auctionEndTime, String image, String auctionPicType, String description) {
        this.auctionName = auctionName;
        this.minPrice = minPrice;
        this.startPrice = startPrice;
        setAuctionStartTime(auctionStartTime);
        setAuctionEndTime(auctionEndTime);
        this.image = image;
        this.auctionPicType = auctionPicType;
        this.description = description;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public String getAuctionName() {
        return auctionName;
    }

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
    }

    public Long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Long minPrice) {
        this.minPrice = minPrice;
    }

    public Long getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Long startPrice) {
        this.startPrice = startPrice;
    }

    public Date getAuctionStartTime() {
        return auctionStartTime;
    }

    public void setAuctionStartTime(Date auctionStartTime) {
        this.auctionStartTime = auctionStartTime;
    }

    public void setAuctionStartTime(String auctionStartTime) {
        try {
            this.auctionStartTime = DateUtil.toDate(auctionStartTime);
        } catch (DateUtil.DateTextSyntaxErrorException e) {
            e.printStackTrace();
        }
    }

    public Date getAuctionEndTime() {
        return auctionEndTime;
    }

    public void setAuctionEndTime(Date auctionEndTime) {
        this.auctionEndTime = auctionEndTime;
    }

    public void setAuctionEndTime(String auctionEndTime) {
        try {
            this.auctionEndTime = DateUtil.toDate(auctionEndTime);
        } catch (DateUtil.DateTextSyntaxErrorException e) {
            e.printStackTrace();
        }
    }

    public String getImage() {
        return image;
    }

    public void setImage(String auctionPic) {
        this.image = auctionPic;
    }

    public String getAuctionPicType() {
        return auctionPicType;
    }

    public void setAuctionPicType(String auctionPicType) {
        this.auctionPicType = auctionPicType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String auctionDesc) {
        this.description = auctionDesc;
    }
}
