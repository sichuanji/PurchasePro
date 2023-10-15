package mapper;

import pojo.Supper;

public interface SupperMapper {

    Supper selectByName(String name);

    int deleteByPrimaryKey(Integer id);

    int insert(Supper record);

    int insertSelective(Supper record);

    Supper selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Supper record);

    int updateByPrimaryKey(Supper record);
}