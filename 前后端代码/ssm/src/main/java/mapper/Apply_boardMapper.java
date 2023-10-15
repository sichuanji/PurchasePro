package mapper;

import pojo.Apply_board;
import pojo.Apply_for_shortlist;

import java.util.List;

public interface Apply_boardMapper {


    List<Apply_board> selectAll();
    int deleteByPrimaryKey(Integer id);

    int insert(Apply_board record);

    int insertSelective(Apply_board record);

    Apply_board selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Apply_board record);

    int updateByPrimaryKey(Apply_board record);
}