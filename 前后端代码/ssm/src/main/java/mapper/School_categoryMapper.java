package mapper;

import dto.Message;
import pojo.School_administrator;
import pojo.School_category;

import java.util.List;

public interface School_categoryMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(School_category record);

    int insertSelective(School_category record);

    School_category selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(School_category record);

    int updateByPrimaryKey(School_category record);

    List<School_administrator> selectAll();

    School_category selectByName(String schoolName);

    List<School_category> selectSchoolByName(String school_name);
}