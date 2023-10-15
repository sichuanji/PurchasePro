package service;

import dto.Message;
import dto.Selecte;
import pojo.Apply_for_shortlist;
import pojo.Bidding_application;
import pojo.President;

/**
 * @author nihao
 * @time 2021/4/16
 */
public interface PresidentService {
    //查询预算
    Message getBudget(int cid);
    // 查询已经使用的钱
    Message getUsedMoney(int cid);

    public Message supplierApply(Bidding_application ba);


    public Message supApplys(int id) ;

    public Message applyUpdate(Bidding_application ba) ;


    public Message applySelect(String t1,String t2,int sid);

    public Message applyDelete (int id);


    public Message supResult(int sid) ;


    public Message infoUpdate(President president);


    public Message applyShortList(Apply_for_shortlist afs);
    public Message applyShortListUpdate(Apply_for_shortlist afs);

    public Message applyShortListSelect(String t1,String t2,int sid);

    public Message applyShortListDelete (int id);

    Message getPurchaseList(int cid);

    Message selectDetail(Selecte selecte);

    Message getPurchaseList1(int cid);

    Message purchaseApprove(int id);

    Message purchaseScreen(Selecte selecte);
}
