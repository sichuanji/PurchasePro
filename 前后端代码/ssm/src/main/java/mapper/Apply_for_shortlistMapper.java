package mapper;

import pojo.Apply_for_shortlist;

import java.util.List;

public interface Apply_for_shortlistMapper {
    List<Apply_for_shortlist> selectAll();

    List<Apply_for_shortlist> getApplyShortList(int sid);
    int deleteByPrimaryKey(Integer id);

    int insert(Apply_for_shortlist record);

    int insertSelective(Apply_for_shortlist record);

    Apply_for_shortlist selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Apply_for_shortlist record);

    int updateByPrimaryKey(Apply_for_shortlist record);

    Apply_for_shortlist getApplyForList(int sid);

    int deleteBySid(int id);

    Apply_for_shortlist selectBySupplierId(Integer sid);
}