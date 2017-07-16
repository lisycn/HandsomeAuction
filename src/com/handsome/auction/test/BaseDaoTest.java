package com.handsome.auction.test;

import com.handsome.auction.bean.Auction;
import com.handsome.auction.bean.Record;
import com.handsome.auction.bean.User;
import com.handsome.auction.dao.*;
import com.handsome.auction.dao.base.BaseDao;
import com.handsome.auction.dao.base.DaoFactory;
import com.wang.db2.Query;
import com.wang.db2.Where;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * by wangrongjun on 2017/7/12.
 */
public class BaseDaoTest {

    private UserDao userDao;
    private AuctionDao auctionDao;
    private RecordDao recordDao;

    @Test
    public void test() {
        dropAndCreateTable();

        // 添加用户 --------------------------------------------------
        User 张三 = new User("张三", "123", "44018", "13023726701", "广州市番禺区", "511400", 0, "老爸？", "张三丰");
        User 李四 = new User("李四", "123", "44018", "13023726702", "广州市荔湾区", "511400", 0, "老爸？", "李四光");
        //英俊是管理员
        User 英俊 = new User("英俊", "123", "44018", "13023726700", "广州市花都区", "511400", 1, "老爸？", "英雄");
        userDao.insert(张三);
        userDao.insert(李四);
        userDao.insert(英俊);
        for (int i = 1; i <= 30; i++) {
            User user = new User("用户" + i, "123", "身份证号" + i, "电话" + i,
                    "广州市" + i + "区", "5114" + i, 0, "question", "answer");
            userDao.insert(user);
        }
        // 添加用户 --------------------------------------------------


        // 添加商品--------------------------------------------------
        Auction 袜子 = new Auction("张三的袜子", 1L, 3L, "2016-04-08", "2016-04-14", "admin/pic_auction/1.jpg", "jpg", "保证不臭");
        Auction 底裤 = new Auction("iPhone8", 2L, 6L, "2016-04-10", "2016-04-17", "admin/pic_auction/1.jpg", "jpg", "黑色的");
        Auction 手机 = new Auction("李四的手机", 100L, 2000L, "2016-04-16", "2016-04-24", "admin/pic_auction/1.jpg", "jpg", "一部好手机");
        Auction 电脑 = new Auction("李四的电脑", 200L, 3000L, "2016-05-01", "2016-05-30", "admin/pic_auction/1.jpg", "jpg", "Acer");
        auctionDao.insert(袜子);
        auctionDao.insert(底裤);
        auctionDao.insert(手机);
        auctionDao.insert(电脑);
        for (int i = 1; i <= 30; i++) {
            Auction auction = new Auction("竞拍品" + i, (long) (i * 5), (long) (i * 20), "2016-07-" + i,
                    "2016-08-" + i, "admin/pic_auction/" + 1 + ".jpg", "jpg", "描述" + i);
            auctionDao.insert(auction);
        }
        // 添加商品--------------------------------------------------


        // 添加记录----------------------------------------------------
        recordDao.insert(new Record(张三, 袜子, "2016-04-09", 3L));
        recordDao.insert(new Record(李四, 袜子, "2016-04-10", 4L));
        recordDao.insert(new Record(张三, 袜子, "2016-04-11", 6L));
        recordDao.insert(new Record(李四, 袜子, "2016-04-12", 8L));
        recordDao.insert(new Record(李四, 底裤, "2016-04-11", 6L));
        recordDao.insert(new Record(张三, 底裤, "2016-04-13", 8L));
        recordDao.insert(new Record(张三, 手机, "2016-04-13", 2000L));
        recordDao.insert(new Record(张三, 手机, "2016-04-15", 2100L));
        recordDao.insert(new Record(张三, 手机, "2016-04-18", 2200L));
        for (int i = 1; i <= 30; i++) {
            recordDao.insert(new Record(i % 2 == 0 ? 张三 : 李四, 电脑, "2016-05-" + i, (long) (3000 + 200 * i)));
        }
        // 添加记录----------------------------------------------------


        // 测试userDao -------------------------------------------------
        assertEquals("张三", userDao.query(Where.build("userName", "张三")).get(0).getUsername());

        List<String> userExpectedList = Arrays.asList("英俊", "用户9", "用户8", "用户7", "用户6");
        List<User> userActualList = userDao.query(new Query().limit(0, 5).orderBy("-root", "-username"));
        assertEquals(userExpectedList.size(), userActualList.size());
        for (int i = 0; i < userActualList.size(); i++) {
            assertEquals(userExpectedList.get(i), userActualList.get(i).getUsername());
        }

        assertEquals(1, userDao.queryCount(Where.build("root", "1")));
        assertEquals(32, userDao.queryCount(Where.build("root", "0")));
        // 测试userDao -------------------------------------------------
    }

    private void dropAndCreateTable() {
        recordDao.dropTable();
        auctionDao.dropTable();
        userDao.dropTable();

        userDao.createTable();
        auctionDao.createTable();
        recordDao.createTable();
        recordDao.createForeignKey();
    }

    @Before
    public void init() {
        userDao = DaoFactory.getUserDao();
        auctionDao = DaoFactory.getAuctionDao();
        recordDao = DaoFactory.getRecordDao();
    }

    @After
    public void close() {
        BaseDao.closeSessionFactory();
    }

}
