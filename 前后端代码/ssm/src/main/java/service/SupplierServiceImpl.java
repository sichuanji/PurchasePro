package service;

import dto.Message;
import dto.Selecte;
import mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author nihao
 * @time 2021/4/16
 */
@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    Bidding_applicationMapper bidding_applicationMapper;
    @Autowired
    Apply_for_shortlistMapper apply_for_shortlistMapper;
    @Autowired
    ResultMapper resultMapper;
    @Autowired
    SupplierMapper supplierMapper;
    @Autowired
    Purchasing_itemsMapper purchasing_itemsMapper;
    @Autowired
    Academy_categoryMapper academy_categoryMapper;
    @Autowired
    School_categoryMapper school_categoryMapper;
    @Autowired
    Product_categoryMapper product_categoryMapper;

    @Autowired
    Apply_boardMapper apply_boardMapper;

    Message msg;

    @Override
    public Message getApplyBoard(){
        List<Apply_board> apply_boards = null;
        msg = new Message();
        try{
      apply_boards =apply_boardMapper.selectAll();}
        catch(Exception e){

            msg.setSuccess(false);
            msg.setMsg("获取公告列表失败");
            return msg;

        }

            msg.setSuccess(true);
            msg.setMsg("获取申请列表成功");
            msg.setDate(apply_boards);
            msg.setStatus(1);
        return msg;

    }

    @Override
    public Message getPurchaseMaxID() {
        Purchasing_items purchasing_items = purchasing_itemsMapper.selectLastInsert();
        msg= new Message();
        msg.setStatus(purchasing_items.getId()+1);

        return msg;
    }

    //申请竞标接口
    @Override
    public Message supplierApply(Bidding_application ba) {
        // List<Bidding_application> bidding_applications = bidding_applicationMapper.selectBySupplierId(ba.getId());
        //查询某个采购的所有记录
        List<Bidding_application> bidding_applications = bidding_applicationMapper.selectByPurchaingItemId(ba.getPurid());
        //判断申请人员是否为同一人员，且商品名相同
        System.out.println(ba.getId());
        ba.setId(null);
        List<Bidding_application> bs = bidding_applicationMapper.selectBySid(ba.getId());
        System.out.println("zheli" + bs.toString());
        if (bs.size() > 0) {
            msg = new Message();
            msg.setSuccess(false);
            msg.setStatus(0);
            msg.setMsg("你已经申请过该项目");
            return msg;
        }
        for (Bidding_application b : bidding_applications) {
            if (b.getProduct_name().equals(ba.getProduct_name()) && b.getSid().equals(ba.getSid())) {
                msg = new Message();
                msg.setSuccess(false);
                msg.setStatus(0);
                msg.setMsg("你已经申请过该类产品");
                return msg;
            }
        }

        int i = 0;
        try {

            i = bidding_applicationMapper.insert(ba);
        } catch (Exception e) {
            msg = new Message();
            msg.setSuccess(false);
            msg.setStatus(0);
            msg.setMsg("你已经申请过该类产品");
            return msg;
        }
        if (i != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("申请成功");
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setStatus(0);
            msg.setMsg("申请失败");
        }
        return msg;
    }

    //供应商申请列表接口
    @Override
    public Message supApplys(int sid) {
        List<Bidding_application> bidList = bidding_applicationMapper.getBidList(sid);
        if (bidList.size() != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("获取申请列表成功");
            msg.setDate(bidList);
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("获取申请列表失败");
        }
        return msg;
    }

    //供应商申请列表更新
    @Override
    public Message applyUpdate(Bidding_application ba) {
        int i = 0;
        i = bidding_applicationMapper.updateByPrimaryKey(ba);
        List<Bidding_application> list = null;
        if (i != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("更新成功");
            list.add(bidding_applicationMapper.selectByPrimaryKey(ba.getId()));
            msg.setDate(list);
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("更新失败");
        }
        return msg;
    }

    //申请筛选
    @Override
    public Message applySelect(String t1, String t2, int sid) {
        int time1 = Integer.parseInt(t1);
        int time2 = Integer.parseInt(t2);
        int time = 0;
        List<Bidding_application> list = bidding_applicationMapper.getBidList(sid);

        List<Bidding_application> list2 = null;
//        for (Bidding_application i :
//               list ) {
//            time = Integer.parseInt(i.getData());
//            if (time<time2&&time>time1){
//                list2.add(i);
//            }
//        }
        if (list2.size() != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("查询成功");
            msg.setDate(list2);
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("未查询到相关信息");
            msg.setStatus(0);

        }
        return msg;
    }

    @Override
    public Message applyDelete(int id) {
        int i = 0;
        i = bidding_applicationMapper.deleteByPrimaryKey(id);

        if (i != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("删除申请成功");
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("删除申请失败");
            msg.setStatus(0);
        }
        return msg;
    }

    //供应商申请结果接口
    @Override
    public Message supResult(int sid) {
        List<Result> results = resultMapper.getSupResultList(sid);
        if (results.size() != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("获取供应商结果列表成功");
            msg.setDate(results);
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("获取供应商结果列表失败");
        }
        return msg;

    }

    //供应商个人信息修改
    @Override
    public Message infoUpdate(Supplier sup) {
        int i = 0;
        i = supplierMapper.updateByPrimaryKey(sup);
        List<Supplier> list = new ArrayList<>();
        if (i != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("用户个人信息更新成功");
            list.add(supplierMapper.selectByPrimaryKey(sup.getId()));
            msg.setDate(list);
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("用户个人信息更新失败");
        }
        return msg;
    }

    //申请入围
    @Override
    public Message applyShortList(Apply_for_shortlist afs) {
//        if(apply_for_shortlistMapper.getApplyForList(afs.getSid()))
        Apply_for_shortlist apply_for_shortlist = apply_for_shortlistMapper.selectBySupplierId(afs.getSid());
        if (apply_for_shortlist != null) {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("对不起，你已经申请了");
            return msg;
        }


        File file = new File("D:\\R_normal_soft\\idea\\study\\ssm\\supplyFile\\supSupplyFile\\" + afs.getSid() + ".pdf");
        File file1 = new File("D:\\R_normal_soft\\idea\\study\\ssm\\supplyFile\\supSupplyFile\\" + afs.getSid() + ".doc");
        System.out.println("nihao");
        System.out.println(afs.getSid());

        if (!file.exists()&&!file1.exists()) {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("请先上传文件");
            return msg;
        }

        int i = 0;
        i = apply_for_shortlistMapper.insert(afs);
        if (i != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("申请成功");
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setStatus(0);
            msg.setMsg("申请失败");
        }
        return msg;
    }

    @Override
    public Message applyShortListUpdate(Apply_for_shortlist afs) {
        int i = 0;
        i = apply_for_shortlistMapper.updateByPrimaryKey(afs);
        List<Bidding_application> list = null;
        if (i != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("更新成功");
            list.add(bidding_applicationMapper.selectByPrimaryKey(afs.getId()));
            msg.setDate(new ArrayList<Bidding_application>());
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("更新失败");
        }
        return msg;
    }

    @Override
    public Message applyShortListSelect(String t1, String t2, int sid) {

        return null;
    }

    @Override
    //供应商id
    public Message applyShortListDelete(int id) {
        try {
            int i = 0;
            i = apply_for_shortlistMapper.deleteBySid(id);

            File file = new File("D:\\R_normal_soft\\idea\\study\\ssm\\supplyFile\\supSupplyFile\\" + id + ".doc");
            Supplier supplier = supplierMapper.selectByPrimaryKey(id);
            supplier.setAccess(0);
            supplierMapper.updateByPrimaryKeySelective(supplier);
            if (!file.exists()) {
            } else {
                file.delete();
            }


            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("删除申请成功");
            msg.setStatus(1);
        } catch (Exception e) {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("删除申请失败");
            msg.setStatus(0);
        }

        return msg;
    }

    @Override
    public Message getApplyList(int id) {
        List<Bidding_application> bidList = bidding_applicationMapper.getBidList(id);
        if (bidList.size() != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("获取到" + bidList.size() + "条数据");
            msg.setDate(bidList);
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("获取申请列表失败");
        }
        return msg;
    }

    @Override
    public Message getPurchaseList() {
        List<Purchasing_items> list = purchasing_itemsMapper.selectAll();
        if (list.size() != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("获取到" + list.size() + "条数据");
            msg.setDate(list);
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("获取申请列表失败");
        }
        return msg;
    }

    @Override
    public Message deleteApply(Bidding_application ba) {
        if (resultMapper.selectByPIId(ba.getPurid()) != null) {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("获取申请列表失败,已经截至不能修改");
            return msg;
        }
        int i = bidding_applicationMapper.deleteByPrimaryKey(ba.getId());
        if (i != 0) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("取消申请成功");
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("取消申请失败");
        }
        return msg;
    }

    @Override
    public Message getResultListBySupplierId(int sid) {

        System.out.println("155819257hjkdhsfauhghsajkl");
        System.out.println(sid + "155819257hjkdhsfauhghsajkl");
        List<Result> list = resultMapper.selectBySupplierId(sid);
        System.out.println(list);
        System.out.println();
        if (list != null) {
            msg = new Message();
            msg.setSuccess(true);
            msg.setMsg("获取到" + list.size() + "条数据");
            msg.setDate(list);
            msg.setStatus(1);
        } else {
            msg = new Message();
            msg.setSuccess(false);
            msg.setMsg("获取结果列表失败");
        }
        return msg;
    }

    @Override
    public Message getApplyForList(int sid) {
        Apply_for_shortlist apply_for_shortlist = apply_for_shortlistMapper.getApplyForList(sid);
        List<Apply_for_shortlist> list = new ArrayList<>();

        list.add(apply_for_shortlist);
        msg = new Message();
        msg.setSuccess(true);
        msg.setMsg("获取到" + list.size() + "条数据");
        msg.setDate(list);
        msg.setStatus(1);

        return msg;
    }

    @Override
    public Message applyListScreen(Selecte selecte) {
        System.out.println(selecte.toString());
        //selecte.getAcademy()
        //查询该供应商的申请
        List<Bidding_application> bidding_applications = bidding_applicationMapper.selectBySupplierId(Integer.valueOf(selecte.getType()));
        List<Bidding_application> bidding_applications1 = new ArrayList<>();
        int i = 0;
        //判断是否需要学院的筛选
        if (selecte.getAcademy() != null) {
            Academy_category academy_category = academy_categoryMapper.selectByName(selecte.getAcademy());
            if (academy_category != null) {
                for (Bidding_application item : bidding_applications
                ) {
                    //获得每个采购的学院号
                    Purchasing_items purchasing_items = purchasing_itemsMapper.selectByPrimaryKey(item.getPurid());
                    if (academy_category.getCid() == purchasing_items.getCid()) {
                        bidding_applications1.add(item);
                    }
                }
                //清空内容
                bidding_applications.clear();
                for (Bidding_application item : bidding_applications1
                ) {
                    bidding_applications.add(item);
                }
                bidding_applications1.clear();

            }
        }
//产品类别筛选
        if (selecte.getCategories() != null) {

            Product_category product_category = product_categoryMapper.selectByName(selecte.getCategories());

            if (product_category != null) {
                for (Bidding_application item : bidding_applications
                ) {
                    Purchasing_items purchasing_items = purchasing_itemsMapper.selectByPrimaryKey(item.getPurid());

                    if (product_category.getPid() == purchasing_items.getPid()) {
                        bidding_applications1.add(item);
                    }
                }
                //清空内容
                bidding_applications.clear();
                for (Bidding_application item : bidding_applications1
                ) {
                    bidding_applications.add(item);
                }
                bidding_applications1.clear();

            }
        }
        //list list1 null
        if (selecte.getValue1().get(0) != null || selecte.getValue1().get(1) != null) {
            int m = selecte.getValue1().get(1).compareTo(selecte.getValue1().get(0));

            if (m != 0) {
                if (selecte.getValue1().get(0) == null) {
                    for (Bidding_application item : bidding_applications
                    ) {
                        int res2 = item.getData().compareTo(selecte.getValue1().get(0));
                        if (res2 >= 0) {
                            bidding_applications1.add(item);
                        }
                    }
                } else if (selecte.getValue1().get(0) == null) {
                    for (Bidding_application item : bidding_applications
                    ) {
                        int res = item.getData().compareTo(selecte.getValue1().get(1));
                        if (res <= 0) {
                            bidding_applications1.add(item);
                        }
                    }

                } else {
                    for (Bidding_application item : bidding_applications
                    ) {
                        int res = item.getData().compareTo(selecte.getValue1().get(1));
                        int res2 = item.getData().compareTo(selecte.getValue1().get(0));
                        if (res <= 0 && res2 >= 0) {
                            bidding_applications1.add(item);
                        }
                    }

                }
                bidding_applications.clear();
                for (Bidding_application item : bidding_applications1
                ) {
                    bidding_applications.add(item);
                }
                bidding_applications1.clear();
            }
        }
//        if (selecte.getType() != null) {
//            // item.getErrol_type()==2
//            if (selecte.getType() == "结果公告") {
//                for (Purchasing_items item : purchasing_items
//                ) {
//                    if (item.getErrol_type() == 2) {
//                        purchasing_items1.add(item);
//                    }
//                }
//            } else {
//                for (Purchasing_items item : purchasing_items
//                ) {
//                    if (item.getErrol_type() == 1) {
//                        purchasing_items1.add(item);
//                    }
//                }
//            }
//            purchasing_items.clear();
//            for (Purchasing_items item : purchasing_items1
//            ) {
//                purchasing_items.add(item);
//            }
//            purchasing_items1.clear();
//        }
        msg = new Message();
        msg.setSuccess(true);
        msg.setMsg("查询到" + bidding_applications.size() + "条记录");
        msg.setDate(bidding_applications);
        msg.setStatus(1);
        return msg;
    }

    @Override
    public Message getApplyResultList(int sid) {
        //获取采购列表
        List<Purchasing_items> purchasing_items = purchasing_itemsMapper.selectAll();

        //获取学院
        List<Academy_category> academy_categories = academy_categoryMapper.selectAll();
        //获取商品类别表
        //List<School_category> school_categories = school_categoryMapper.selectAll();
        List<Product_category> product_categories = product_categoryMapper.selectAll();
        //通过id查询发出的申请
        List<Bidding_application> bidding_applications = bidding_applicationMapper.selectBySupplierId(sid);
        //查询结果订单
        List<Result> results = resultMapper.selectBySupplierId(sid);
        List<SupplierDetail> supplierDetails = new ArrayList<>();
        System.out.println("length="+purchasing_items.size());
        for (Bidding_application bd : bidding_applications) {
            System.out.println("下一条");
            SupplierDetail supplierDetail = new SupplierDetail();
                for (int len=0;len<purchasing_items.size();len++) {
                    System.out.println(bd.getPurid());
                    System.out.println("=?"+purchasing_items.get(len).getId());
                if (bd.getPurid().toString().equals( purchasing_items.get(len).getId().toString())){//这个订单

                    System.out.println("符合" +" "+purchasing_items.get(len).getId());
                    supplierDetail.setBoardName(purchasing_items.get(len).getPurchase_name());
                    supplierDetail.setReallyPrice(bd.getRealy_price());
                    supplierDetail.setProductName(purchasing_items.get(len).getProduct_name());
                    supplierDetail.setDate(bd.getData());
                    supplierDetail.setId(purchasing_items.get(len).getId());
                    for (Product_category pc : product_categories) {
                        if (purchasing_items.get(len).getPid() == pc.getPid()) {//这个订单
                            supplierDetail.setCategory(pc.getProduct_name());
                        }
                    }
                    Integer sidI =sid;
                    if (purchasing_items.get(len).getIs_result() != null) {//已经出结果
                        supplierDetail.setB(1);
                        for (Result re : results) {
                            if (re.getPid().toString().equals( purchasing_items.get(len).getId().toString())) {
                               // System.out.println(re.getSid()+"uye"+sid);

                                if (re.getSid().toString().equals(sidI.toString())) {
                                    supplierDetail.setB(2);
                                } else {
                                    supplierDetail.setB(1);
                                }
                            }

                        }
                    } else {
                        supplierDetail.setB(0);
                    }
                    supplierDetails.add(supplierDetail);
                    break;
                }


            }

        }

        msg = new Message();
        msg.setSuccess(true);
        msg.setDate(supplierDetails);
        msg.setMsg("查询到" + supplierDetails.size() + "条数据");
        return msg;
    }

    @Override
    public Message getSearchBidingList(Selecte selecte) {
        //获取采购列表
        List<Purchasing_items> purchasing_items = purchasing_itemsMapper.selectAll();

        //获取学院
        List<Academy_category> academy_categories = academy_categoryMapper.selectAll();
        //获取商品类别表
        //List<School_category> school_categories = school_categoryMapper.selectAll();
        List<Product_category> product_categories = product_categoryMapper.selectAll();
        //通过id查询发出的申请
        List<Bidding_application> bidding_applications = bidding_applicationMapper.selectBySupplierId(Integer.valueOf(selecte.getType()));
        //查询结果订单
        List<Result> results = resultMapper.selectBySupplierId(Integer.valueOf(selecte.getType()));
        List<SupplierDetail> supplierDetails = new ArrayList<>();
        List<SupplierDetail> supplierDetails1 = new ArrayList<>();
        System.out.println(selecte.toString());
        int acid=-1;
        if(selecte.getAcademy()!=null){
            for(Academy_category ac: academy_categories){
                if(selecte.getAcademy().equals(ac.getAcademy_name())){
                    acid= ac.getCid();
                    break;
                }
            }
        }
        for (Bidding_application bd : bidding_applications) {
            SupplierDetail supplierDetail = new SupplierDetail();

            for (Purchasing_items p : purchasing_items) {
                if(acid==-1 || p.getCid()==acid){
                if (bd.getPurid() == p.getId()) {//这个订单

                    supplierDetail.setBoardName(p.getPurchase_name());
                    supplierDetail.setReallyPrice(bd.getRealy_price());
                    supplierDetail.setProductName(p.getProduct_name());
                    supplierDetail.setDate(bd.getData());
                    for (Product_category pc : product_categories) {
                        if (p.getPid() == pc.getPid()) {//这个订单
                            supplierDetail.setCategory(pc.getProduct_name());
                        }
                    }
                    if (p.getIs_result() != null) {//已经出结果
                        supplierDetail.setB(1);

                        for (Result re : results) {
                            if (re.getPid() == p.getId()) {
                                if (re.getSid() == Integer.valueOf(selecte.getType())) {
                                    supplierDetail.setB(2);
                                } else {
                                    supplierDetail.setB(1);
                                }
                            }
                        }

                    } else {
                        supplierDetail.setB(0);
                    }
                    supplierDetails.add(supplierDetail);
                }

            }

            }

        }
        //学院
        if(selecte.getCategories()!=null){
        for(SupplierDetail s : supplierDetails){
            if(s.getCategory().equals(selecte.getCategories())){
                supplierDetails1.add(s);
            }
        }
        supplierDetails.clear();
            for(SupplierDetail s : supplierDetails1){
                supplierDetails.add(s);
            }
            supplierDetails1.clear();
        }
        msg = new Message();
        msg.setSuccess(true);
        msg.setDate(supplierDetails);
        msg.setMsg("查询到" + supplierDetails.size() + "条数据");
        return msg;
    }

}
