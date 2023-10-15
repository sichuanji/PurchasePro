package service;

import dto.Message;
import dto.Selecte;
import dto.TimeSelectDto;
import pojo.*;

/**
 * @author nihao
 * @time 2021/4/16
 */
public interface PurchaserService {





    Message purchaseApply(Purchasing_items pi);

    Message purchaseApplyUpdate(Purchasing_items pi);

    Message purchaseApplySelect(int id);

    Message purchaseApplyDelete(int id);

    Message purchaseApplyAllSelect(int id);

    Message purchaseApplyAllSelectFilter(TimeSelectDto tsd);

    Message purchaseApplyResultFilter(TimeSelectDto tsd);

    Message purchaseApplyResult(int id);

    Message InfoSelect(int id);

    Message InfoUpdate(Purchaser purchaser);

    Message purchaseScreen(Selecte selecte);

    Message purchaseScreen1(Selecte selecte);

    Message getPurchaseById(Purchasing_items pi);
}
