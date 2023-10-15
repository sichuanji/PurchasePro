package service;

import dto.Message;
import dto.Selecte;
import dto.TimeSelectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Apply_for_shortlist;
import pojo.Bidding_application;
import pojo.Supplier;

/**
 * @author nihao
 * @time 2021/4/16
 */
public interface SupplierService {

    public Message supplierApply(Bidding_application ba);


    public Message supApplys(int id) ;

    public Message applyUpdate(Bidding_application ba) ;


    public Message applySelect(String t1,String t2,int sid);

    public Message applyDelete (int id);


    public Message supResult(int sid) ;


    public Message infoUpdate(Supplier sup);


    public Message applyShortList(Apply_for_shortlist afs);
    public Message applyShortListUpdate(Apply_for_shortlist afs);

    public Message applyShortListSelect(String t1,String t2,int sid);

    public Message applyShortListDelete (int id);

    Message getApplyList(int id);

    Message getPurchaseList();

    Message deleteApply(Bidding_application ba);

    Message getResultListBySupplierId(int sid);

    Message getApplyForList(int sid);

    Message applyListScreen(Selecte selecte);

    Message getApplyResultList(int sid);

    Message getSearchBidingList(Selecte selecte);

    Message getApplyBoard();
    Message getPurchaseMaxID();

}
