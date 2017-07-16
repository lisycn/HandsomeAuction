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
        // TODO 以后把分页和查询条件分离后，可以只在查询条件改变时查数量，换页就不用查询
        long totalCount = service.getCount(auction);
        List<Auction> auctionList = service.getAuctionList(auction, currentPage, pageSize);
        ActionContext.getContext().put("auctionList", auctionList);
        ActionContext.getContext().put("currentPage", currentPage);
        long totalPage = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
        ActionContext.getContext().put("totalPage", totalPage);
        ActionContext.getContext().put("hadGetAuctionList", true);// 防止查询后集合为空，页面再次跳转。

        return checkAdmin();
    }

    public String getAuctionInfo() throws Exception {
        String admin = checkAdmin();
        if (!admin.equals("manager")) {//如果未登录或者不是管理员
            return admin;
        }
        Auction auction = service.getAuction(this.auction.getAuctionId());
        if (auction == null) {
            ActionContext.getContext().put("msg", "商品不存在，auctionId=" + this.auction.getAuctionId());
            return ERROR;
        }
        ActionContext.getContext().put("auction", auction);
        return "manager";
    }

    public String updateAction() throws Exception {
        String admin = checkAdmin();
        if (!admin.equals("manager")) {//如果未登录或者不是管理员
            return admin;
        }
        if (service.update(auction)) {
            ActionContext.getContext().put("msg", "修改成功！商品名：" + auction.getAuctionName());
            ActionContext.getContext().put("auction", auction);
            return SUCCESS;
        } else {
            ActionContext.getContext().put("msg", "修改失败！商品名：" + auction.getAuctionName());
            return ERROR;
        }
    }

    public String deleteAction() throws Exception {
        String admin = checkAdmin();
        if (!admin.equals("manager")) {//如果未登录或者不是管理员
            return admin;
        }
        if (service.delete(auction.getAuctionId())) {
            ActionContext.getContext().put("msg", "删除成功！商品名：" + auction.getAuctionName());
            ActionContext.getContext().put("auction", auction);
            return SUCCESS;
        } else {
            ActionContext.getContext().put("msg", "删除失败！商品名：" + auction.getAuctionName());
            return ERROR;
        }
    }

}
