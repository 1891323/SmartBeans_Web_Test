package smartbeans.portal.member.login.service.impl;

import org.springframework.stereotype.Service;
import smartbeans.portal.member.login.mapper.MemberLoginMapper;
import smartbeans.portal.member.login.service.MemberLoginService;
import smartbeans.portal.member.login.service.MemberVO;

import javax.annotation.Resource;
import java.util.List;

@Service("MemberLoginService")
public class MemberLoginServiceImpl implements MemberLoginService {

    @Resource(name = "memberLoginMapper")
    private MemberLoginMapper memberLoginMapper;


    /**
     * 일반 로그인을 처리한다
     * @param inputMemberVO
     * @return MemberVO
     * @throws Exception
     */
    @Override
    public MemberVO selectMember(MemberVO inputMemberVO) throws Exception {

        // 1. 입력한 비밀번호를 암호화한다. (현재 비밀번호에 DB에 암호화 처리된 회원 없어서 비밀번호 암호화 처리 비활성화)
//        String enpassword = EgovFileScrty.encryptPassword(vo.getPassword(), vo.getId());
//        vo.setPassword(enpassword)

        // 2. 아이디와 암호화된 비밀번호가 DB와 일치하는지 확인한다.
        MemberVO memberVO = memberLoginMapper.actionLogin(inputMemberVO);

        // 3. 결과를 리턴한다
        if(memberVO != null && !memberVO.getUserId().equals("") && !memberVO.getPassword().equals("")) {
            return memberVO;
        } else {
            memberVO = new MemberVO();
        }
        return memberVO;
    }



}
