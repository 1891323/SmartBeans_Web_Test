package smartbeans.portal.member.login.service;



public interface MemberLoginService {

    /**
     * 일반 로그인을 처리한다
     *
     * @param memberVO
     * @throws Exception
     */
    MemberVO selectMember(MemberVO memberVO) throws Exception;
}
