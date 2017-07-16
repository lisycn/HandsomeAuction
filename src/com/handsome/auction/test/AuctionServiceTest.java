package com.handsome.auction.test;

import com.handsome.auction.bean.Auction;
import com.handsome.auction.service.AuctionService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * by wangrongjun on 2017/7/14.
 */
public class AuctionServiceTest {

    private AuctionService service = new AuctionService();

    @Test
    public void test() {
        Auction where = new Auction();
        where.setAuctionStartTime("2016-07-11");
        where.setAuctionEndTime("2016-08-20");

        assertEquals(10, service.getCount(where));

        List<Auction> auctionList = service.getAuctionList(where, 1, 4);
        assertEquals(4, auctionList.size());
        assertEquals(19, auctionList.get(0).getAuctionId());

        auctionList = service.getAuctionList(where, 2, 4);
        assertEquals(2, auctionList.size());
        assertEquals(23, auctionList.get(0).getAuctionId());
    }

}
