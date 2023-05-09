package chungnam.portal.uat.uia.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import chungnam.cmmn.LoginVO;

@Mapper
@Repository
public interface LoginMapper {
	  /**
     * 2011.08.26
	 * EsntlId를 이용한 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    public LoginVO actionLoginByEsntlId(LoginVO vo) throws Exception;

	/**
	 * 일반 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    public LoginVO actionLogin(LoginVO vo) throws Exception;

    /**
	 * 인증서 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    public LoginVO actionCrtfctLogin(LoginVO vo) throws Exception;

    /**
	 * 아이디를 찾는다.
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    public LoginVO searchId(LoginVO vo) throws Exception;

    /**
	 * 비밀번호를 찾는다.
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    public LoginVO searchPassword(LoginVO vo) throws Exception;
    /**
	 * 변경된 비밀번호를 저장한다.
	 * @param vo LoginVO
	 * @exception Exception
	 */
    public void updatePassword(LoginVO vo) throws Exception;
    
    /**
	 * 로그인인증제한 내역을 조회한다.
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
	public Map<?,?> selectLoginIncorrect(LoginVO vo) throws Exception;
    /**
	 * 로그인인증제한 내역을 업데이트 한다.
	 * @param vo LoginVO
	 * @return vod
	 * @exception Exception
	 */
    public void updateLoginIncorrectENT(Map<?,?> map) throws Exception;
    
    public void updateLoginIncorrectGNR(Map<?,?> map) throws Exception;
    
    public void updateLoginIncorrectUSR(Map<?,?> map) throws Exception;
    
    /**
	 * 비밀번호를 수정한후 경과한 날짜를 조회한다.
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    public int selectPassedDayChangePWD(LoginVO vo) throws Exception;

	/**
	 * 디지털원패스 인증 회원 조회한다.
	 * @param id
	 * @return LoginVO
	 * @exception Exception
	 */
    public LoginVO onepassLogin(String id) throws Exception;
    
    
    //public LoginVO findByUserId(String id) throws Exception;
    
    //public List<> findAuthoritiesByUserId(String id) throws Exception;
}
