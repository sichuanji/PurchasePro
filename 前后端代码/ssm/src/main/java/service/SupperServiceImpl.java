package service;

import dto.Message;
import dto.SchAdmin;
import mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author nihao
 * @time 2021/4/16*/
@Service
public class SupperServiceImpl implements  SupperService{
    @Autowired
    Bidding_applicationMapper bidding_applicationMapper;
    @Autowired
    Apply_for_shortlistMapper apply_for_shortlistMapper;
    @Autowired
    ResultMapper resultMapper;
    @Autowired
    SupplierMapper supplierMapper;
    @Autowired
    School_administratorMapper school_administratorMapper;
    @Autowired
            School_categoryMapper school_categoryMapper;
    @Autowired
            SupperMapper supperMapper;

    Message msg;


    @Override
    public Message selectSchoolAdminByName(String name) {
        int i=0;
        List<School_administrator> list = school_administratorMapper.selectSchoolAdminByName(name);
        msg=new Message();
        msg.setSuccess(true);
        msg.setMsg("查询到"+list.size()+"条数据");
        msg.setDate(list);
        msg.setStatus(1);
        return msg;
    }

    @Override
    public Message selectSchool() {
        int i=0;
        List<School_administrator> list = school_categoryMapper.selectAll();
        msg=new Message();
        msg.setSuccess(true);
        msg.setMsg("查询到"+list.size()+"条数据");
        msg.setDate(list);
        msg.setStatus(1);
        return msg;
    }

    @Override
    public Message resetAdminPwd(int id) {
        School_administrator school_administrator =school_administratorMapper.selectByPrimaryKey(id);

        school_administrator.setPwd("111111");
        int i=school_administratorMapper.updateByPrimaryKey(school_administrator);
        if(i>0){
            msg=new Message();
            msg.setSuccess(true);
            msg.setMsg("重置密码成功");
            msg.setStatus(1);
        }else {
            msg=new Message();
            msg.setSuccess(false);
            msg.setMsg("重置密码失败");
            msg.setStatus(0);
        }
        return msg;
    }

    @Override
    public Message deleteAdmin(int id) {
        int i =0;
        i = school_administratorMapper.deleteByPrimaryKey(id);
        if(i!=0){
            msg=new Message();
            msg.setSuccess(true);
            msg.setMsg("删除账号成功");
            msg.setStatus(1);
        }else {
            msg=new Message();
            msg.setSuccess(false);
            msg.setMsg("删除失败");
            msg.setStatus(0);
        }
        return msg;
    }

    @Override
    public Message infoUpdate(Supper supper) {

        int i =supperMapper.updateByPrimaryKey(supper);
        if(i!=0){
            msg=new Message();
            msg.setSuccess(true);
            msg.setMsg("更新成功");
            msg.setStatus(1);
        }else {
            msg=new Message();
            msg.setSuccess(false);
            msg.setMsg("更新失败");
            msg.setStatus(0);
        }
        return msg;
    }

    @Override
    public Message addSchAdmin(SchAdmin sa) {
        School_category school_category = new School_category();
        school_category.setSchool_name(sa.getSchoolName());
        school_category.setTag(sa.getTag());
        try {
            school_categoryMapper.insert(school_category);
        }catch (Exception e){
            System.out.println("yes,出问题了125");
            msg=new Message();
            msg.setSuccess(false);
            msg.setMsg("该学校已有管理员");
            msg.setStatus(0);
            return msg;
        }
        System.out.println("yes,出问题了132");
        if(school_administratorMapper.selectByName((sa.getName()))!=null){
            msg=new Message();
            msg.setSuccess(false);
            msg.setMsg("用户名重复请重新输入");
            msg.setStatus(0);
            return msg;
        }

        System.out.println("yes,出问题了141");
        school_category=school_categoryMapper.selectByName(sa.getSchoolName());
        if(school_category==null){
            msg=new Message();
            msg.setSuccess(false);
            msg.setMsg("没有这个学校");
            msg.setStatus(0);
            return msg;
        }
        System.out.println("yes,出问题了158");
        School_administrator school_administrator = new School_administrator();
        school_administrator.setSid(school_category.getSid());
        school_administrator.setContract_name(sa.getContract_name());
        school_administrator.setCount(sa.getCount());
        school_administrator.setName(sa.getName());
        school_administrator.setPwd("111111");
        school_administrator.setTel(sa.getTel());
        school_administrator.setTag(sa.getTag());
        school_administrator.setLast_time(new Date());


        int i =school_administratorMapper.insertSelective(school_administrator);
        if(i!=0){
            msg=new Message();
            msg.setSuccess(true);
            msg.setMsg("添加成功");
            msg.setStatus(1);
        }else {
            msg=new Message();
            msg.setSuccess(false);
            msg.setMsg("添加失败");
            msg.setStatus(0);
        }
        return msg;
    }

    @Override
    public Message selectSchoolByName(String name) {
        int i=0;
        List<School_category> list = school_categoryMapper.selectSchoolByName(name);
        msg=new Message();
        msg.setSuccess(true);
        msg.setMsg("查询到"+list.size()+"条数据");
        msg.setDate(list);
        msg.setStatus(1);
        return msg;
    }
}
