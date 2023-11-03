package smartbeans.portal.common.auth.service.impl;

import smartbeans.cmmn.LoginVO;
import smartbeans.portal.common.auth.mapper.AuthMapper;
import smartbeans.portal.common.auth.service.AuthService;
import smartbeans.portal.uat.uia.service.EgovLoginService;
import smartbeans.portal.utl.sim.service.EgovFileScrty;
import org.egovframe.rte.fdl.cmmn.exception.EgovBizException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("AuthService")
public class AuthServiceImpl implements AuthService {

    @Resource(name = "loginService")
    EgovLoginService loginService;

    @Resource(name = "AuthMapper")
    AuthMapper authMapper;

    @Override
    public void chagePassword(LoginVO loginVO) throws Exception {

        String enpasswordNew = EgovFileScrty.encryptPassword(loginVO.getPasswordNew(), loginVO.getId());

        LoginVO changeLoginVO = new LoginVO();
        changeLoginVO.setId(loginVO.getId());
        changeLoginVO.setPassword(loginVO.getPassword());
        changeLoginVO.setPasswordNew(enpasswordNew);

        // 현재 비밀번호 확인
        LoginVO checkLoginVO = loginService.actionLogin(changeLoginVO);
        if (checkLoginVO == null || checkLoginVO.getId() == null || checkLoginVO.getId().equals("")) {
            throw new EgovBizException("현재 비밀번호가 일치하지 않습니다.");
        }

        // 비밀번호 변경, 사용자상태 승인으로 변경
        authMapper.updatePasswordAndApprove(changeLoginVO);

    }
}
