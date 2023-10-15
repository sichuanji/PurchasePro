package mapper;

import pojo.Purchaser;
import pojo.Supplier;

import java.util.List;

public interface SupplierMapper {
    //通过名字查询
    Supplier selectByName(String name);
    //
    List<Supplier> selectAll();
    int deleteByPrimaryKey(Integer id);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    Supplier selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);
}