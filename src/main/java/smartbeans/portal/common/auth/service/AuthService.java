package smartbeans.portal.common.auth.service;

import smartbeans.cmmn.LoginVO;

public interface AuthService {

    void chagePassword(LoginVO changeLoginVO) throws Exception;
}
