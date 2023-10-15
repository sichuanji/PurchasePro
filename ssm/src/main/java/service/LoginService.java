package service;

import dto.Login;
import dto.Message;

/**
 * @author nihao
 * @time 2021/3/13
 */
public interface LoginService {
    /**
     * login 服务
     * @param supplier
     * @return
     */
    Message supplierLogin(Login supplier);
    Message presidentLogin(Login president);
    Message schoolAdministratorLogin(Login school_administrator);
    Message purchaserLogin(Login purchaser);
    Message supperLogin(Login supper);
    Message administerLogin(Login login);

}
