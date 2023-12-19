package smartbeans.portal.member.login.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import smartbeans.cmmn.EgovMessageSource;
import smartbeans.cmmn.LoginVO;
import smartbeans.cmmn.util.EgovUserDetailsHelper;
import smartbeans.portal.member.login.service.MemberLoginService;
import smartbeans.portal.member.login.service.MemberVO;
import smartbeans.portal.utl.fcc.service.EgovStringUtil;
import smartbeans.portal.utl.sim.vo.EgovClntInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MemberLoginController {

    @Resource(name = "smartbeansLoginService")
    private MemberLoginService memberLoginService;

    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;


    /**
     * 로그인을 화면으로 들어간다
     * @param
     * @return      로그인 페이지
     * @throws      Exception
     */
    @RequestMapping(value = "/member/login.do")
    public String memberLoginView(@ModelAttribute("MemberVO") MemberVO MemberVO
                            , HttpServletRequest request
                            , HttpServletResponse response
                            , ModelMap model) throws Exception {

        return "member/login/memberLoginUser.index"; // view 반환
    }


    /**
     * 일반(세션) 로그인을 처리한다
     * @param memberVO
     * @param request
     * @param model
     * @return 로그인결과(세션정보)
     * @throws Exception
     */
    @RequestMapping(value = "/member/login/actionLogin.do")
    public String actionMemberLogin(//@ModelAttribute("MemberVO") MemberVO memberVO
              HttpServletRequest request
            , ModelMap model
            , MemberVO memberVO
            ) throws Exception {

        // 로그인 정보로 회원 정보 조회
        MemberVO resultVO = memberLoginService.selectMember(memberVO);

        return "redirect:/member/login/actionMain.do";
    }

    /**
     * 로그인 후 메인화면으로 들어간다
     * @param
     * @return 로그인 페이지
     * @exception Exception
     */
    @RequestMapping(value = "/member/login/actionMain.do")
    public String actionMain(HttpServletRequest request,ModelMap model) throws Exception {

        // 1. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("loginMessage", egovMessageSource.getMessage("fail.common.login"));
            return "redirect:/uat/uia/egovLoginUsr.do";
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        if (user.getIp().equals(""))
            user.setIp(EgovClntInfo.getClntIP(request));

        // 221116	김혜준	2022 시큐어코딩 조치
        System.out.println("User Id : {}"+ EgovStringUtil.isNullToString(user.getId()));

        // 3. 메인 페이지 이동
        if (user.getUserSe().equals("USR")) {
            return "member/login/memberLoginTest.index";
        } else {
            return "member/login/memberLoginTest.index";
        }
    }


}