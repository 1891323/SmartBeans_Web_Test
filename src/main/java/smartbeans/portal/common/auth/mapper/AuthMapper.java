package smartbeans.portal.common.auth.mapper;

import smartbeans.cmmn.LoginVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper("AuthMapper")
public interface AuthMapper {

    void updatePasswordAndApprove(LoginVO loginVO);
    void updatePasswordAndApplicate(LoginVO loginVO);

}
