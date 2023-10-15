package controller;

import dto.Message;
import dto.SchAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pojo.*;
import service.SupperService;
import service.SupplierService;

import javax.naming.Name;
import java.util.Map;

/**
 * @author nihao
 * @time 2021/4/14
 */

@Controller
@RequestMapping("supper")
@CrossOrigin(origins = "*")
public class SupperController {
    @Autowired
    SupplierService supplierService;
    @Autowired
    SupperService supperService;

    //添加学校管理员用户
    @RequestMapping(value = "testAddSchAdmin", method = RequestMethod.POST)
    @ResponseBody
    public String testAddSchAdmin(@RequestBody School_administrator sa) {
        //return supperService.addSchAdmin(sa);
        return sa.getContract_name();
    }

    //添加学校管理员用户
    @RequestMapping(value = "addSchAdmin", method = RequestMethod.POST)
    @ResponseBody
    public Message addSchAdmin(@RequestBody SchAdmin sa) {
        return supperService.addSchAdmin(sa);
    }

    //通过学校名查询
    @RequestMapping(value = "selectSchoolAdminByName")
    @ResponseBody
    public Message selectSchoolAdminByName(String name) {
        return supperService.selectSchoolAdminByName(name);
    }

    //查询学校管理员用户
    @RequestMapping(value = "selectSchoolByName")
    @ResponseBody
    public Message selectSchoolByName(String name) {
        return supperService.selectSchoolByName(name);
    }

    //查询学校管理员用户
    @RequestMapping(value = "selectSchool")
    @ResponseBody
    public Message selectSchool(String name) {
        return supperService.selectSchool();
    }

    //重置密码
    @RequestMapping(value = "resetAdminPwd")
    @ResponseBody
    public Message resetAdminPwd(int id) {
        return supperService.resetAdminPwd(id);
    }

    //删除密码
    @RequestMapping(value = "deleteAdmin")
    @ResponseBody
    public Message deleteAdmin(int id) {
        return supperService.deleteAdmin(id);
    }

    // 供应商申请结果接口
    @RequestMapping(value = "infoUpdate", method = RequestMethod.POST)
    //@PostMapping("supplierLogin")
    @ResponseBody
    public Message infoUpdate(@RequestBody Supper supper) {
        return supperService.infoUpdate(supper);
    }


}
