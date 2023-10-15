package mapper;

import dto.TimeSelectDto;
import pojo.Purchasing_items;

import java.util.List;

public interface Purchasing_itemsMapper {

    List<Purchasing_items> selectByCid(Integer cid);

    List<Purchasing_items> selectByTimeAndPidFilter(TimeSelectDto tsd);
    List<Purchasing_items> selectAll();
    Purchasing_items selectLastInsert();
    int deleteByPrimaryKey(Integer id);

    int insert(Purchasing_items record);

    int insertSelective(Purchasing_items record);

    Purchasing_items selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Purchasing_items record);

    int updateByPrimaryKey(Purchasing_items record);
}