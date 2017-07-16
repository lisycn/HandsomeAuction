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

        // ����û� --------------------------------------------------
        User ���� = new User("����", "123", "44018", "13023726701", "�����з�خ��", "511400", 0, "�ϰ֣�", "������");
        User ���� = new User("����", "123", "44018", "13023726702", "������������", "511400", 0, "�ϰ֣�", "���Ĺ�");
        //Ӣ���ǹ���Ա
        User Ӣ�� = new User("Ӣ��", "123", "44018", "13023726700", "�����л�����", "511400", 1, "�ϰ֣�", "Ӣ��");
        userDao.insert(����);
        userDao.insert(����);
        userDao.insert(Ӣ��);
        for (int i = 1; i <= 30; i++) {
            User user = new User("�û�" + i, "123", "���֤��" + i, "�绰" + i,
                    "������" + i + "��", "5114" + i, 0, "question", "answer");
            userDao.insert(user);
        }
        // ����û� --------------------------------------------------


        // �����Ʒ--------------------------------------------------
        Auction ���� = new Auction("����������", 1L, 3L, "2016-04-08", "2016-04-14", "admin/pic_auction/1.jpg", "jpg", "��֤����");
        Auction �׿� = new Auction("iPhone8", 2L, 6L, "2016-04-10", "2016-04-17", "admin/pic_auction/1.jpg", "jpg", "��ɫ��");
        Auction �ֻ� = new Auction("���ĵ��ֻ�", 100L, 2000L, "2016-04-16", "2016-04-24", "admin/pic_auction/1.jpg", "jpg", "һ�����ֻ�");
        Auction ���� = new Auction("���ĵĵ���", 200L, 3000L, "2016-05-01", "2016-05-30", "admin/pic_auction/1.jpg", "jpg", "Acer");
        auctionDao.insert(����);
        auctionDao.insert(�׿�);
        auctionDao.insert(�ֻ�);
        auctionDao.insert(����);
        for (int i = 1; i <= 30; i++) {
            Auction auction = new Auction("����Ʒ" + i, (long) (i * 5), (long) (i * 20), "2016-07-" + i,
                    "2016-08-" + i, "admin/pic_auction/" + 1 + ".jpg", "jpg", "����" + i);
            auctionDao.insert(auction);
        }
        // �����Ʒ--------------------------------------------------


        // ��Ӽ�¼----------------------------------------------------
        recordDao.insert(new Record(����, ����, "2016-04-09", 3L));
        recordDao.insert(new Record(����, ����, "2016-04-10", 4L));
        recordDao.insert(new Record(����, ����, "2016-04-11", 6L));
        recordDao.insert(new Record(����, ����, "2016-04-12", 8L));
        recordDao.insert(new Record(����, �׿�, "2016-04-11", 6L));
        recordDao.insert(new Record(����, �׿�, "2016-04-13", 8L));
        recordDao.insert(new Record(����, �ֻ�, "2016-04-13", 2000L));
        recordDao.insert(new Record(����, �ֻ�, "2016-04-15", 2100L));
        recordDao.insert(new Record(����, �ֻ�, "2016-04-18", 2200L));
        for (int i = 1; i <= 30; i++) {
            recordDao.insert(new Record(i % 2 == 0 ? ���� : ����, ����, "2016-05-" + i, (long) (3000 + 200 * i)));
        }
        // ��Ӽ�¼----------------------------------------------------


        // ����userDao -------------------------------------------------
        assertEquals("����", userDao.query(Where.build("userName", "����")).get(0).getUsername());

        List<String> userExpectedList = Arrays.asList("Ӣ��", "�û�9", "�û�8", "�û�7", "�û�6");
        List<User> userActualList = userDao.query(new Query().limit(0, 5).orderBy("-root", "-username"));
        assertEquals(userExpectedList.size(), userActualList.size());
        for (int i = 0; i < userActualList.size(); i++) {
            assertEquals(userExpectedList.get(i), userActualList.get(i).getUsername());
        }

        assertEquals(1, userDao.queryCount(Where.build("root", "1")));
        assertEquals(32, userDao.queryCount(Where.build("root", "0")));
        // ����userDao -------------------------------------------------
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
