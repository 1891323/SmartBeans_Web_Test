package smartbeans.portal.member.login.mapper;

import org.apache.ibatis.annotations.Mapper;
import smartbeans.portal.member.login.service.MemberVO;

import java.util.List;

@Mapper
public interface MemberLoginMapper {


    /**
     * 아이디, 패스워드가 일치하는 회원 찾기
     * @param inputMemberVO
     * @return MemberVO
     */
    MemberVO actionLogin(MemberVO inputMemberVO);
}