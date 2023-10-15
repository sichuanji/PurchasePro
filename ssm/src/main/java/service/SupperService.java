package service;

import dto.Message;
import dto.SchAdmin;
import pojo.*;

/**
 * @author nihao
 * @time 2021/4/16
 */
public interface SupperService {

     Message selectSchoolAdminByName(String name);

    Message selectSchool();

    Message resetAdminPwd(int id);

    Message deleteAdmin(int id);

    Message infoUpdate(Supper supper);

    Message addSchAdmin(SchAdmin sa);

    Message selectSchoolByName(String name);
}
