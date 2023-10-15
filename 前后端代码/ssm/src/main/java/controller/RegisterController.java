package controller;

import dto.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import dto.Message;
import pojo.*;
import service.Register;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author nihao
 * @time 2021/3/20
 */

@CrossOrigin(origins = "*")
@Controller
public class RegisterController {
    @Autowired
    Register register;


    /**
     * 超级用户的检测
     * @param name
     * @return
     */
    @RequestMapping(value = "administerCheck",method = RequestMethod.POST)
    @ResponseBody
    public Message administerCheck(@RequestBody String name){
        return register.administerCheck(name);
    }

    /**
     * 对将要注册的供应商用户名进行检测
     * 传入一个Login对象
     * 返回一个message
     * @param name
     * @return
     */
    @RequestMapping(value = "supplierCheck",method = RequestMethod.POST)
    @ResponseBody
    public Message supplierCheck(@RequestBody String name){
        return register.supplierCheckUser(name);
    }




    /**
     * 传入一个supplier
     * 返回一个message
     * @param supplier
     * @return
     */
    @RequestMapping(value = "supplierRegister",method = RequestMethod.POST)
    @ResponseBody
    public Message supplierRegister(@RequestBody Supplier supplier)  {
        System.out.println(supplier.toString());

        System.out.println(supplier.getPwd());
        return register.supplierRegister(supplier);
       // return null;
    }

    @RequestMapping(value = "purchaserRegister",method = RequestMethod.POST)
    @ResponseBody
    public Message purchaserRegister(@RequestBody Purchaser purchaser)  {
        return register.purchaserRegister(purchaser);
    }
    @RequestMapping(value = "presidentRegister",method = RequestMethod.POST)
    @ResponseBody
    public Message presidentRegister(@RequestBody President president)  {
        return register.presidentRegister(president);
    }
    @RequestMapping(value = "schoolRegister",method = RequestMethod.POST)
    @ResponseBody
    public Message schoolRegister(@RequestBody School_administrator school_administrator)  {
        return register.schoolRegister(school_administrator);
    }
    @RequestMapping(value = "supperRegister",method = RequestMethod.POST)
    @ResponseBody
    public Message supperRegister(@RequestBody Supper supper)  {
        return register.superRegister(supper);
    }

}
