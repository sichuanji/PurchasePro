package mapper;

import pojo.Academy_category;

import java.util.List;

public interface Academy_categoryMapper {
    List<Academy_category> selectAcademyListByName(String name) ;
    List<Academy_category> selectAll();

    int deleteByPrimaryKey(Integer cid);

    int insert(Academy_category record);

    int insertSelective(Academy_category record);

    Academy_category selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(Academy_category record);

    int updateByPrimaryKey(Academy_category record);

    Academy_category selectByName(String name);
}