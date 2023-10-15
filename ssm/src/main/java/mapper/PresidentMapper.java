package mapper;

import pojo.President;

import java.util.List;

public interface PresidentMapper {
    President selectByName(String name);

    int deleteByPrimaryKey(Integer id);

    int insert(President record);

    int insertSelective(President record);

    President selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(President record);

    int updateByPrimaryKey(President record);

    List<President> selectPresidentListByName(String name);

    List<President> selectByCid(Integer cid);
}