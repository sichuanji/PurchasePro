package service;

import com.sun.tools.internal.xjc.reader.gbind.ElementSets;
import dto.Board;
import dto.Message;
import dto.Selecte;
import mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;

import javax.xml.crypto.Data;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author nihao
 * @time 2021/4/14
 */
@Service
public class BaseAcademyServiceImpl implements BaseAcademyService {
    @Autowired
    Academy_categoryMapper academy_categoryMapper;
    @Autowired
    Product_categoryMapper product_categoryMapper;
    @Autowired
    ResultMapper resultMapper;
    @Autowired
    Purchasing_itemsMapper purchasing_itemsMapper;
    @Autowired
    SupplierMapper supplierMapper;
@Autowired
        Bidding_applicationMapper bidding_applicationMapper;
@Autowired
    PurchaserMapper purchaserMapper;
    Message message;

    @Override
    public Message GetProductCategory() {
        List<Product_category> product_categories = product_categoryMapper.selectAll();
        if (product_categories.size() != 0) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("获取产品类别成功");
            message.setDate(product_categories);
            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("获取产品类别失败");
        }
        return message;
    }

    @Override
    public Message GetAcademyCategory() {
        List<Academy_category> academy_categories = academy_categoryMapper.selectAll();
        if (academy_categories.size() != 0) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("获取产品类别成功");
            message.setDate(academy_categories);
            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("获取产品类别失败");
        }
        return message;
    }

    @Override
    public Message GetResultCategory() {
        List<Result> results = resultMapper.selectAll();
        if (results.size() != 0) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("获取结果列表成功");
            message.setDate(results);
            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("获取结果列表失败");
        }
        return message;
    }

    @Override
    public Message GetPurchasingCategory() {
        List<Purchasing_items> purchasing_items = purchasing_itemsMapper.selectAll();
        StringBuffer sb = new StringBuffer();
//        for (Purchasing_items i:
//             purchasing_items) {
//            sb.delete(0,sb.length());
//            sb.append(i.getDate()).insert(4,"/");
//            sb.insert(7,"/");
//            i.setDate(sb.toString());
//            sb.delete(0,sb.length());
//            sb.append(i.getDeadline()).insert(4,"/");
//            sb.insert(7,"/");
//            i.setDeadline(sb.toString());
//        }
        if (purchasing_items.size() != 0) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("获取采购列表成功");
            message.setDate(purchasing_items);
            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("获取采购列表失败");
        }
        return message;
    }

    @Override
    public Message GetPurchasingCategory1() {
        List<Purchasing_items> purchasing_items = purchasing_itemsMapper.selectAll();

        StringBuffer sb = new StringBuffer();
//        for (Purchasing_items i:
//                purchasing_items) {
//            sb.delete(0,sb.length());
//            sb.append(i.getDate()).insert(4,"/");
//            sb.insert(7,"/");
//            i.setDate(sb.toString());
//        }
        if (purchasing_items.size() != 0) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("获取采购列表成功");
            message.setDate(purchasing_items);
            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("获取采购列表失败");
        }
        return message;
    }

    @Override
    public Message GetBoardList() {
        List<Purchasing_items> purchasing_items = purchasing_itemsMapper.selectAll();
        List<Purchasing_items> list = new ArrayList<>();
        for (Purchasing_items p:purchasing_items){

            if(p.getAcceess()==11&&p.getPurchase_name()!=null&&p.getPurchase_name().length()>0){
                System.out.println("p.getPurchase_name()"+p.getPurchase_name());
                list.add(p);
            }
        }

        if (purchasing_items.size() != 0) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("获取到"+list.size()+"条数据");
            message.setDate(list);
            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("获取采购列表失败");
        }
        return message;
    }

    @Override
    public Message GetBoard(int id) {
        Purchasing_items purchasing_items = null;
        List<Purchasing_items> list = new ArrayList<>();

        try {
            purchasing_items = purchasing_itemsMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("查询失败");
            return message;
        }
        if (purchasing_items != null) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("获取采购列表成功");
            list.add(purchasing_items);
            message.setDate(list);
            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("获取采购列表失败");
        }
        return message;
    }

    @Override
    public Message updateBoard(Purchasing_items purchasing_items) {

        int i = purchasing_itemsMapper.updateByPrimaryKey(purchasing_items);
        if (i == 1) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("更新成功");

            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("获更新失败");
        }
        return message;
    }

    @Override
    public Message getResult(int id) {
        Result result = null;
        List<Result> list = new ArrayList<>();

        try {
            result = resultMapper.selectByPIId(id);
        } catch (Exception e) {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("查询失败");
            return message;
        }
        if (result != null) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("获取结果成功");
            list.add(result);
            message.setDate(list);
            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("获取结果失败");
        }
        return message;
    }

    @Override
    public Message getSupplier(int id) {
        Supplier supplier = null;
        List<Supplier> list = new ArrayList<>();

        try {
            supplier = supplierMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("查询失败");
            return message;
        }
        if (supplier != null) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("获取中标供应商成功");
            list.add(supplier);
            message.setDate(list);
            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("获取结中标供应商失败");
        }
        return message;
    }

    @Override
    public String getFileName(int id) {
        //Result result = resultMapper.selectByPrimaryKey(id);
        Purchasing_items purchasing_items = purchasing_itemsMapper.selectByPrimaryKey(id);
        String name = purchasing_items.getProduct_name() + "采购审批表";
        return name;
        //return "采购审批表";
    }

    @Override
    public Message boardScreen(Selecte selecte) {
        System.out.println(selecte.toString());
        List<Purchasing_items> purchasing_items = purchasing_itemsMapper.selectAll();
        List<Purchasing_items> purchasing_items1 = new ArrayList<>();
        int i = 0;
        //判断是否需要学院的筛选
        if (selecte.getAcademy()!= null) {
            Academy_category academy_category = academy_categoryMapper.selectByName(selecte.getAcademy());
            if (academy_category != null) {
                for (Purchasing_items item : purchasing_items
                ) {
                    if (academy_category.getCid() == item.getCid()) {
                        purchasing_items1.add(item);
                    }
                }
                //清空内容
                purchasing_items.clear();
                for (Purchasing_items item : purchasing_items1
                ) {
                    purchasing_items.add(item);
                }
                purchasing_items1.clear();

            }
        }

        if (selecte.getCategories() != null) {
            Product_category product_category = product_categoryMapper.selectByName(selecte.getCategories());

            if (product_category != null) {
                for (Purchasing_items item : purchasing_items
                ) {
                    if (product_category.getPid() == item.getPid()) {
                        purchasing_items1.add(item);
                    }
                }
                //清空内容
                purchasing_items.clear();
                for (Purchasing_items item : purchasing_items1
                ) {
                    purchasing_items.add(item);
                }
                purchasing_items1.clear();

            }
        }
        //list list1 null
       if (selecte.getValue1().get(0) != null || selecte.getValue1().get(1) != null) {
           int m = selecte.getValue1().get(1).compareTo(selecte.getValue1().get(0));

           if(m!=0){
            if (selecte.getValue1().get(0) == null) {
                for (Purchasing_items item : purchasing_items
                ) {
                    int res2 = item.getPublish_time().compareTo(selecte.getValue1().get(0));
                    if (res2 >= 0) {
                        purchasing_items1.add(item);
                    }
                }
            } else if (selecte.getValue1().get(0) == null) {
                for (Purchasing_items item : purchasing_items
                ) {
                    int res = item.getPublish_time().compareTo(selecte.getValue1().get(1));
                    if (res <= 0) {
                        purchasing_items1.add(item);
                    }
                }

            } else {
                for (Purchasing_items item : purchasing_items
                ) {
                    int res = item.getPublish_time().compareTo(selecte.getValue1().get(1));
                    int res2 = item.getPublish_time().compareTo(selecte.getValue1().get(0));
                    if (res <= 0 && res2 >= 0) {
                        purchasing_items1.add(item);
                    }
                }

            }
            purchasing_items.clear();
            for (Purchasing_items item : purchasing_items1
            ) {
                purchasing_items.add(item);
            }
            purchasing_items1.clear();
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
        for(Purchasing_items p:purchasing_items){
            if(p.getAcceess()==11&&p.getPurchase_name()!=null&&p.getPurchase_name().length()>0){
                purchasing_items1.add(p);
            }
        }
        purchasing_items.clear();
            for (Purchasing_items item : purchasing_items1
            ) {
                purchasing_items.add(item);
            }
            purchasing_items1.clear();
        message = new Message();
        message.setSuccess(true);
        message.setMsg("查询到" + purchasing_items.size() + "条记录");
        message.setDate(purchasing_items);
        message.setStatus(1);
        return message;
    }

    @Override
    public Message addPurBoard(Purchasing_items purchasing_items) {
        System.out.println(purchasing_items.getId()+"我有id吗");
        purchasing_items.setPublish_time(new Date());
        int i = purchasing_itemsMapper.updateByPrimaryKey(purchasing_items);
        if (i == 1) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("插入成功");
            //purchasing_items = purchasing_itemsMapper.selectLastInsert();

            List<Purchasing_items> list = purchasing_itemsMapper.selectByCid(purchasing_items.getCid());
            message.setDate(list);
            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("插入失败");
        }
        return message;
    }

    @Override
    public Message getBoardListByCid(int cid) {
        System.out.println(cid);
        List<Purchasing_items> list = new ArrayList<>();
        List<Purchasing_items> purchasing_items =null;
        if (cid==0){
             purchasing_items = purchasing_itemsMapper.selectAll();
        }
        else {
             purchasing_items = purchasing_itemsMapper.selectByCid(cid);
        }

        for (Purchasing_items p:purchasing_items){
            if(p.getAcceess()>=1){
                list.add(p);
            }
        }
            message = new Message();
            message.setSuccess(true);
            message.setMsg("查询到"+list.size()+"条数据");
            message.setDate(list);
            message.setStatus(1);

        return message;
    }

    @Override
    public Message editPurBoard(Purchasing_items purchasing_items) {
        int i = purchasing_itemsMapper.updateByPrimaryKey(purchasing_items);
        if (i == 1) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("修改成功");
            //purchasing_items = purchasing_itemsMapper.selectLastInsert();
            List<Purchasing_items> list = purchasing_itemsMapper.selectByCid(purchasing_items.getCid());
            message.setDate(list);
            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("修改失败");
        }
        return message;
    }

    @Override
    public Message deletePurBoard(Purchasing_items purchasing_items) {
        int i = purchasing_itemsMapper.deleteByPrimaryKey(purchasing_items.getId());
        if (i == 1) {
            message = new Message();
            message.setSuccess(true);
            message.setMsg("删除成功");
            //purchasing_items = purchasing_itemsMapper.selectLastInsert();

            message.setStatus(1);
        } else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("删除失败");
        }
        return message;
    }
//获取用户名称用于文件名
    @Override
    public String getSupplyFileName(int id) {
        Supplier supplier = supplierMapper.selectByPrimaryKey(id);

        return supplier.getCompany_name()+"申请表"+".docx";
        //return "采购审批表";
    }

    @Override
    public String getPurchaseFileName(int id) {
        Purchasing_items purchasing_items = purchasing_itemsMapper.selectByPrimaryKey(id);
        return purchasing_items.getProduct_name()+"申请表"+".docx";
    }

    @Override
    public Message getApplyCount(int purid) {

        int count =0;
                count =bidding_applicationMapper.getApplyCount(purid);

            message = new Message();
            message.setSuccess(true);
            message.setMsg("查询成功");
            message.setStatus(count);


        return message;
    }

    @Override
    public Message getMinimumPrice(int purid) {
        List<Bidding_application> list =bidding_applicationMapper.selectByPurchaingItemId(purid);
        int minimum=1000000;
        for(Bidding_application b:list){
            if (b.getRealy_price()<minimum){
                minimum = b.getRealy_price();
            }
        }
        if(minimum==1000000){
            minimum=0;
        }
        message = new Message();
        message.setSuccess(true);
        message.setMsg("查询成功");
        message.setStatus(minimum);
        return message;
    }

    @Override
    public Message addResultBoard(int id) {
        Result result = resultMapper.selectByPIId(id);
        if(result!=null){
            message = new Message();
            message.setSuccess(false);
            message.setMsg("对不起，你已经发布结果公告");
            return message;
        }
        Purchasing_items purchasing_items = purchasing_itemsMapper.selectByPrimaryKey(id);
        int val1= purchasing_items.getEnd_time().compareTo(new Date());
        if(val1>=0){
            message = new Message();
            message.setSuccess(false);
            message.setMsg("对不起，还未截至，不能发布公告");
            return message;
        }

        purchasing_items.setIs_result(1);
        int i = purchasing_itemsMapper.updateByPrimaryKey(purchasing_items);
        List<Bidding_application> list =bidding_applicationMapper.selectByPurchaingItemId(id);
        if(list.size()==0){
            message = new Message();
            message.setSuccess(false);
            message.setMsg("对不起,没人参与竞标，竞标失败");
            return message;
        }
        int minimun=1000000;
        if(list.size()!=0)
         minimun=list.get(0).getRealy_price();
        int thisId =0;
        for (Bidding_application b:list) {
            if(b.getRealy_price()<minimun){
                minimun = b.getRealy_price();
                thisId= b.getId();
            }
        }

        File file =new File("D:\\R_normal_soft\\idea\\study\\ssm\\boardFile\\resultFile\\"+id+".pdf");
        System.out.println("D:\\R_normal_soft\\idea\\study\\ssm\\boardFile\\reultFile\\"+id+".pdf");
        if(!file.exists()){
            message = new Message();
            message.setSuccess(false);
            message.setMsg("请先上传文件");
            return  message;
        }


        Result result1 = new Result();
        result1.setPid(purchasing_items.getId());
        for(Bidding_application bd:list){
            if(bd.getId()==thisId)
                result1.setSid(bd.getSid());//设置供应商id
        }
        //result1.setSid(list.get(thisId).getSid());//设置供应商id
        result1.setScis(1);
        result1.setPurchase_id(purchaserMapper.selectByCid(purchasing_items.getCid()).getId());
        result1.setActual_product_name(purchasing_items.getProduct_name());
        result1.setReality_price(minimun);
        result1.setDate(new Date());
        int j = resultMapper.insert(result1);


        if(i==1&&j==1){
            message = new Message();
            message.setSuccess(true);
            message.setMsg("插入成功");
            return message;
        }else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("插入失败");
            return message;
        }


    }

    @Override
    public Message getAllBoardListByCid(int cid) {
        List<Purchasing_items> purchasing_items = purchasing_itemsMapper.selectByCid(cid);
        List<Purchasing_items> list =new ArrayList<>();
        for(Purchasing_items p:purchasing_items){
            if(p.getAcceess()==11&&p.getPurchase_name()!=null&&p.getPurchase_name().length()>0){
                list.add(p);
            }
        }

        message = new Message();
        message.setSuccess(true);
        message.setMsg("查询到"+list.size()+"条数据");
        message.setDate(list);
        message.setStatus(1);

        return message;
    }

    @Override
    public Message boardScreen1(Selecte selecte) {
        System.out.println(selecte.toString());
        //selecte.getAcademy()
        List<Purchasing_items> purchasing_items = purchasing_itemsMapper.selectAll();
        List<Purchasing_items> purchasing_items1 = new ArrayList<>();
        int i = 0;
        //判断是否需要学院的筛选
        if (selecte.getAcademy()!= null) {
            Academy_category academy_category = academy_categoryMapper.selectByName(selecte.getAcademy());
            if (academy_category != null) {
                for (Purchasing_items item : purchasing_items
                ) {
                    if (academy_category.getCid() == item.getCid()) {
                        purchasing_items1.add(item);
                    }
                }
                //清空内容
                purchasing_items.clear();
                for (Purchasing_items item : purchasing_items1
                ) {
                    purchasing_items.add(item);
                }
                purchasing_items1.clear();

            }
        }

        if (selecte.getCategories() != null) {
            Product_category product_category = product_categoryMapper.selectByName(selecte.getCategories());

            if (product_category != null) {
                for (Purchasing_items item : purchasing_items
                ) {
                    if (product_category.getPid() == item.getPid()) {
                        purchasing_items1.add(item);
                    }
                }
                //清空内容
                purchasing_items.clear();
                for (Purchasing_items item : purchasing_items1
                ) {
                    purchasing_items.add(item);
                }
                purchasing_items1.clear();

            }
        }
        //list list1 null
        if (selecte.getValue1().get(0) != null || selecte.getValue1().get(1) != null) {
            int m = selecte.getValue1().get(1).compareTo(selecte.getValue1().get(0));

            if(m!=0){
                if (selecte.getValue1().get(0) == null) {
                    for (Purchasing_items item : purchasing_items
                    ) {
                        int res2 = item.getPublish_time().compareTo(selecte.getValue1().get(0));
                        if (res2 >= 0) {
                            purchasing_items1.add(item);
                        }
                    }
                } else if (selecte.getValue1().get(0) == null) {
                    for (Purchasing_items item : purchasing_items
                    ) {
                        int res = item.getPublish_time().compareTo(selecte.getValue1().get(1));
                        if (res <= 0) {
                            purchasing_items1.add(item);
                        }
                    }

                } else {
                    for (Purchasing_items item : purchasing_items
                    ) {
                        int res = item.getPublish_time().compareTo(selecte.getValue1().get(1));
                        int res2 = item.getPublish_time().compareTo(selecte.getValue1().get(0));
                        if (res <= 0 && res2 >= 0) {
                            purchasing_items1.add(item);
                        }
                    }

                }
                purchasing_items.clear();
                for (Purchasing_items item : purchasing_items1
                ) {
                    purchasing_items.add(item);
                }
                purchasing_items1.clear();
            }
        }
        if (selecte.getType() != null) {
            // item.getErrol_type()==2
            if (selecte.getType() == "结果公告") {
                for (Purchasing_items item : purchasing_items
                ) {
                    if (item.getErrol_type() == 2) {
                        purchasing_items1.add(item);
                    }
                }
            } else {
                for (Purchasing_items item : purchasing_items
                ) {
                    if (item.getErrol_type() == 1) {
                        purchasing_items1.add(item);
                    }
                }
            }
            purchasing_items.clear();
            for (Purchasing_items item : purchasing_items1
            ) {
                purchasing_items.add(item);
            }
            purchasing_items1.clear();
        }
        message = new Message();
        message.setSuccess(true);
        message.setMsg("查询到" + purchasing_items.size() + "条记录");
        message.setDate(purchasing_items);
        message.setStatus(1);
        return message;
    }

    @Override
    public Message getChartList(int id) {
        System.out.println(id);
        System.out.println(id);

        message = new Message();
        try {

        List<Result> results = resultMapper.selectBySupplierId(id);
        message.setSuccess(true);
        message.setMsg("查询到"+results.size()+"条数据");
        message.setDate(results);
        message.setStatus(1);
    } catch (Exception e){
            message = new Message();
            message.setSuccess(false);
            message.setMsg("查询失败");
        }
        return message;
    }

    @Override
    public Message getChartAllList() {

        message = new Message();
        try {

            List<Result> results = resultMapper.selectAll();
            message.setSuccess(true);
            message.setMsg("查询到"+results.size()+"条数据");
            message.setDate(results);
            message.setStatus(1);
        } catch (Exception e){
            message = new Message();
            message.setSuccess(false);
            message.setMsg("查询失败");
        }
        return message;
    }

    @Override
    public Message getChartSupplier() {
        message = new Message();
        try {
            List<Result> results = resultMapper.selectAll();
            message.setSuccess(true);
            message.setMsg("查询到"+results.size()+"条数据");
            message.setDate(results);
            message.setStatus(1);
        } catch (Exception e){
            message = new Message();
            message.setSuccess(false);
            message.setMsg("查询失败");
        }
        return message;
    }

    @Override
    public Message getSupplierChart(int id) {
        President president = new President();
        message = new Message();

        president.setSid(0);
        president.setId(0);
        List<Result> results = resultMapper.selectBySupplierId(id);
        for (Result re:results
             ) {
            president.setSid(president.getSid()+re.getReality_price());
            president.setId(president.getId()+1);
            president.setLast_time(re.getDate());
        }
        Supplier supplier = supplierMapper.selectByPrimaryKey(id);
        president.setName(supplier.getCompany_name());
        message.setSuccess(true);
        List<President> presidents = new ArrayList<>();
        presidents.add(president);
        message.setDate(presidents);
        message.setStatus(1);
        return message;
    }
//为采购产品的id
    @Override
    public Message getApplyList(int id) {
        message  = new Message();
        try {
            List<Bidding_application> list = bidding_applicationMapper.selectByPurchaingItemId(id);
            for (Bidding_application ba: list
                 ) {
                Supplier supplier = supplierMapper.selectByPrimaryKey(ba.getSid());
                ba.setTag(supplier.getCompany_name());

            }


            message.setSuccess(true);
            message.setMsg("查询到"+list.size()+"条数据");
            message.setDate(list);
            message.setStatus(1);
        }catch (Exception e){

            message = new Message();
            message.setSuccess(false);
            message.setMsg("查询失败");
        }

        return message;
    }

    @Override
    public Message addResBoard(Bidding_application ba) {

        Result result = resultMapper.selectByPIId(ba.getPurid());
        if(result!=null){
            message = new Message();
            message.setSuccess(false);
            message.setMsg("对不起，你已经发布结果公告");
            return message;
        }

        Purchasing_items purchasing_items = purchasing_itemsMapper.selectByPrimaryKey(ba.getPurid());
        int val1= purchasing_items.getEnd_time().compareTo(new Date());
        if(val1>=0){
            message = new Message();
            message.setSuccess(false);
            message.setMsg("对不起，还未截至，不能发布公告");
            return message;
        }

        purchasing_items.setIs_result(1);
        int i = purchasing_itemsMapper.updateByPrimaryKey(purchasing_items);
//
//        List<Bidding_application> list =bidding_applicationMapper.selectByPurchaingItemId(id);
//        int minimun=1000000;
//        if(list.size()!=0)
//            minimun=list.get(0).getRealy_price();
//        int thisId =0;
//        for (Bidding_application b:list) {
//            if(b.getRealy_price()<minimun){
//                minimun = b.getRealy_price();
//                thisId= b.getId();
//            }
//        }

        File file =new File("D:\\R_normal_soft\\idea\\study\\ssm\\boardFile\\resultFile\\"+ba.getPurid()+".pdf");
        System.out.println("D:\\R_normal_soft\\idea\\study\\ssm\\boardFile\\reultFile\\"+ba.getPurid()+".pdf");
        if(!file.exists()){
            message = new Message();
            message.setSuccess(false);
            message.setMsg("请先上传文件");
            return  message;
        }


        Result result1 = new Result();
//        result1.setPid(purchasing_items.getId());
//        for(Bidding_application bd:list){
//            if(bd.getId()==thisId)
//                result1.setSid(bd.getSid());//设置供应商id
//        }
        //result1.setSid(list.get(thisId).getSid());//设置供应商id
        result1.setPid(ba.getPurid());
        result1.setSid(ba.getSid());//设置供应商id
        result1.setScis(1);
        result1.setPurchase_id(purchaserMapper.selectByCid(purchasing_items.getCid()).getId());
        result1.setActual_product_name(ba.getProduct_name());
        result1.setReality_price(ba.getRealy_price());
        result1.setDate(new Date());
        int j = resultMapper.insert(result1);


        if(i==1&&j==1){
            message = new Message();
            message.setSuccess(true);
            message.setMsg("插入成功");
            return message;
        }else {
            message = new Message();
            message.setSuccess(false);
            message.setMsg("插入失败");
            return message;
        }

    }
}
