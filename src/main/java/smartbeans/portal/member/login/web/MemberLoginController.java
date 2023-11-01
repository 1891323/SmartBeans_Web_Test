package smartbeans.portal.member.login.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import smartbeans.cmmn.EgovComponentChecker;
import smartbeans.cmmn.EgovMessageSource;
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

    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;


    /**
     * 로그인을 처리한다
     * @param       model
     * @return      로그인 페이지
     * @throws      Exception
     */
    @RequestMapping(value = "/member/login")
    public String memberLoginView(@ModelAttribute("MemberVO") MemberVO MemberVO
                            , HttpServletRequest request
                            , HttpServletResponse response
                            , ModelMap model) throws Exception {


        return "member/login/memberLoginUser"; // view 반환
    }


    /**
     * 일반(세션) 로그인을 처리한다
     * @param memberVO
     * @param request
     * @param model
     * @return 로그인결과(세션정보)
     * @throws Exception
     */
    @RequestMapping(value = "/member/login/actionLogin")
    public String actionMemberLogin(//@ModelAttribute("MemberVO") MemberVO memberVO
              HttpServletRequest request
            , ModelMap model
            , MemberVO memberVO
            ) throws Exception {


        // 로그인 정보로 회원 정보 조회
        MemberVO resultVO = memberLoginService.selectMember(memberVO);

        if(resultVO.getUserId() != null && !resultVO.getUserId().isEmpty()) {
            request.getSession().setAttribute("loginVO", resultVO);
            request.getSession().setAttribute("accessUser", resultVO.getUserId());
            System.out.println("세션값 확인 : " + request.getSession().getAttribute("loginVO").toString());
            return "redirect:/";
        } else {
            model.addAttribute("loginMessage", egovMessageSource.getMessage("fail.common.login", request.getLocale()));
            return "redirect:/";
        }
    }


}