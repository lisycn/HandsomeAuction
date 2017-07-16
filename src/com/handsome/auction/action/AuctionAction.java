package com.handsome.auction.action;

import com.handsome.auction.bean.Auction;
import com.handsome.auction.service.AuctionService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

/**
 * by wangrongjun on 2017/7/14.
 */
public class AuctionAction extends BaseAction implements ModelDriven<Auction> {

    private static final int pageSize = 10;

    private AuctionService service = new AuctionService();
    private Auction auction = new Auction();
    private int currentPage;

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public Auction getModel() {
        return auction;
    }

    public String searchAuctions() throws Exception {
        // TODO �Ժ�ѷ�ҳ�Ͳ�ѯ��������󣬿���ֻ�ڲ�ѯ�����ı�ʱ����������ҳ�Ͳ��ò�ѯ
        long totalCount = service.getCount(auction);
        List<Auction> auctionList = service.getAuctionList(auction, currentPage, pageSize);
        ActionContext.getContext().put("auctionList", auctionList);
        ActionContext.getContext().put("currentPage", currentPage);
        long totalPage = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
        ActionContext.getContext().put("totalPage", totalPage);
        ActionContext.getContext().put("hadGetAuctionList", true);// ��ֹ��ѯ�󼯺�Ϊ�գ�ҳ���ٴ���ת��

        return checkAdmin();
    }

    public String getAuctionInfo() throws Exception {
        String admin = checkAdmin();
        if (!admin.equals("manager")) {//���δ��¼���߲��ǹ���Ա
            return admin;
        }
        Auction auction = service.getAuction(this.auction.getAuctionId());
        if (auction == null) {
            ActionContext.getContext().put("msg", "��Ʒ�����ڣ�auctionId=" + this.auction.getAuctionId());
            return ERROR;
        }
        ActionContext.getContext().put("auction", auction);
        return "manager";
    }

    public String updateAction() throws Exception {
        String admin = checkAdmin();
        if (!admin.equals("manager")) {//���δ��¼���߲��ǹ���Ա
            return admin;
        }
        if (service.update(auction)) {
            ActionContext.getContext().put("msg", "�޸ĳɹ�����Ʒ����" + auction.getAuctionName());
            ActionContext.getContext().put("auction", auction);
            return SUCCESS;
        } else {
            ActionContext.getContext().put("msg", "�޸�ʧ�ܣ���Ʒ����" + auction.getAuctionName());
            return ERROR;
        }
    }

    public String deleteAction() throws Exception {
        String admin = checkAdmin();
        if (!admin.equals("manager")) {//���δ��¼���߲��ǹ���Ա
            return admin;
        }
        if (service.delete(auction.getAuctionId())) {
            ActionContext.getContext().put("msg", "ɾ���ɹ�����Ʒ����" + auction.getAuctionName());
            ActionContext.getContext().put("auction", auction);
            return SUCCESS;
        } else {
            ActionContext.getContext().put("msg", "ɾ��ʧ�ܣ���Ʒ����" + auction.getAuctionName());
            return ERROR;
        }
    }

}
