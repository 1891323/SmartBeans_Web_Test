package smartbeans.portal.member.login.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import smartbeans.portal.member.login.service.MemberLoginService;
import smartbeans.portal.member.login.service.MemberVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Member;

@Controller
public class MemberLoginController {

    @Resource(name = "MemberLoginService")
    private MemberLoginService memberLoginService;


    /**
     * 로그인을 처리한다
     * @param       model
     * @return      로그인 페이지
     * @throws      Exception
     */
    //밑에 URL을 가져와서 http://localhost:9090/member/login.do 라고 검색하면 접속 가능함!
    @RequestMapping(value = "member/login.do")
    public String memberLoginView(@ModelAttribute("MemberVO") MemberVO MemberVO
            , HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model) throws Exception {


        return "member/login/memberLoginUser"; // view 반환
    }



    @RequestMapping(value = "/member/login/actionLogin")
    public String actionMemberLogin(@ModelAttribute("MemberVO") MemberVO memberVO
            , HttpServletRequest request
            , ModelMap model) throws Exception {

        // 임시값 입력(프론트 단에서 아직 값을 안 받아오기 때문)
        memberVO.setUserId("user01");
        memberVO.setPassword("pass01");

        // 로그인 정보로 회원 정보 조회
        MemberVO resultVO = memberLoginService.selectMember(memberVO);
        System.out.println("출력값 테스트 : " + resultVO);
        return "layouts/common/TopMenu"; // 메인으로 반환 (이전 페이지로 이동으로 변경할 예정)
    }


}