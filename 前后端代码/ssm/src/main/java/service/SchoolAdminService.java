package service;

import dto.Message;
import dto.PreAdmin;
import dto.Selecte;
import pojo.*;

/**
 * @author nihao
 * @time 2021/4/16
 */
public interface SchoolAdminService {

    public Message selectSchoolAdminByName(String name);

    public Message supplierApply(Bidding_application ba);


    public Message supApplys(int id) ;

    public Message applyUpdate(Bidding_application ba) ;


    public Message applySelect(String t1,String t2,int sid);

    public Message applyDelete (int id);


    public Message supResult(int sid) ;


    public Message infoUpdate(School_administrator sa);


    public Message applyShortList(Apply_for_shortlist afs);
    public Message applyShortListUpdate(Apply_for_shortlist afs);

    public Message applyShortListSelect(String t1,String t2,int sid);

    public Message applyShortListDelete (int id);

    Message selectSearchByAcademyName(String name);

    Message SearchByAcademyName(String name);

    Message addPresidentAdmin(PreAdmin preAdmin);

    Message resetPresidentPwd(int id);

    Message deletePresident(int id);

    Message searchPurchaserByAcademyName(String name);

    Message addPurchaserAdmin(PreAdmin preAdmin);

    Message deletePurchaser(int id);

    Message resetPurchaserPwd(int id);

    Message getSupApplyList(String name);

    Message getSupplierList();

    Message supplyApprove(int id);

    Message getPurchaseList();

    Message purchaseApprove(int id);

    Message selectDetail(Selecte selecte);

    Message getPurchaseList1();

    Message deleteApplyBoard(int id);

    Message addApplyBoard(Apply_board ab);
}
