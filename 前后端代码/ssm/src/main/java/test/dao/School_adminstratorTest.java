package test.dao;

import mapper.School_administratorMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author nihao
 * @time 2021/4/13
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class School_adminstratorTest {
    @Autowired
    School_administratorMapper school_administratorMapper;
    @Test
   public void selectByPrimaryKey(){
    System.out.println(school_administratorMapper.selectByPrimaryKey(1));
}
}
