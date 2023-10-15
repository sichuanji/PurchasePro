package mapper;

import dto.Message;
import pojo.School_administrator;

import java.util.List;

public interface School_administratorMapper {


    List<School_administrator> selectSchoolAdminByName(String name);

    School_administrator selectByName(String name);

    int deleteByPrimaryKey(Integer id);

    int insert(School_administrator record);

    int insertSelective(School_administrator record);

    School_administrator selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(School_administrator record);

    int updateByPrimaryKey(School_administrator record);
}
