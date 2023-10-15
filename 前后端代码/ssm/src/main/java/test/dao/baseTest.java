package test.dao;

import mapper.Purchasing_itemsMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author nihao
 * @time 2021/5/3
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class baseTest {
    @Autowired
    Purchasing_itemsMapper purchasing_itemsMapper;
    @Test
    public  void  getLastInsert(){
        System.out.println(purchasing_itemsMapper.selectLastInsert().toString());
    }
}
