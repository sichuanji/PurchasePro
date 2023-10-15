package mapper;

import pojo.Bidding_application;

import java.util.List;

public interface Bidding_applicationMapper {
    //查询某个供应商的申请详情
    List<Bidding_application> getBidList(int sid);
    int deleteByPrimaryKey(Integer id);

    int insert(Bidding_application record);

    int insertSelective(Bidding_application record);

    Bidding_application selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bidding_application record);

    int updateByPrimaryKey(Bidding_application record);

    List<Bidding_application> selectBySupplierId(Integer id);
    List<Bidding_application> selectBySid(Integer id);
    List<Bidding_application> selectByPurchaingItemId(Integer purid);

    int getApplyCount(int purid);
}