package mapper;

import pojo.Product_category;

import java.util.List;

public interface Product_categoryMapper {
    List<Product_category> selectAll();

    int deleteByPrimaryKey(Integer pid);

    int insert(Product_category record);

    int insertSelective(Product_category record);

    Product_category selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(Product_category record);

    int updateByPrimaryKey(Product_category record);

    Product_category selectByName(String name);
}