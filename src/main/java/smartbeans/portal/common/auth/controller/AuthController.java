package smartbeans.portal.common.auth.controller;

import smartbeans.cmmn.LoginVO;
import smartbeans.portal.common.auth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.egovframe.rte.fdl.cmmn.exception.EgovBizException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class AuthController {


    @Resource(name = "AuthService")
    private AuthService authService;

    @RequestMapping("/auth/login.do")
    public String login(HttpServletRequest request, ModelMap model, String code) throws Exception {
        if (code != null && "failure".equals(code)) {
            model.addAttribute("alertMessage", "로그인에 실패하였습니다.");
        } else if (code != null && "denied".equals(code)) {
            model.addAttribute("alertMessage", "접근 권한이 없습니다.");
        } else if (code != null && "approve".equals(code)) {
            model.addAttribute("alertMessage", "승인이 완료되었습니다.");
        }

        return "/auth/AuthLogin.empty";
    }

    @RequestMapping(value = "/auth/actionLogin.do")
    public String actionLogin(HttpServletRequest request, ModelMap model) throws Exception {
        log.debug("start loginAction");

        return "redirect:/auth/login.do";
    }

    @RequestMapping(value = "/auth/actionLogout.do")
    public String actionLogout(HttpServletRequest request, ModelMap model) throws Exception {
        // 1. Security 연동
		return "redirect:/j_spring_security_logout";
//
//        request.getSession().setAttribute("loginVO", null);
//        request.getSession().setAttribute("accessUser", null);
//
//        return "redirect:/auth/login.do";
    }

    @RequestMapping(value = "/auth/passwordChange.do")
    public String passwordChange(HttpServletRequest request, ModelMap model) throws Exception {
        // 로그인 세션 검사
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
        if (loginVO == null) {
            return "redirect:/auth/login.do";
        }

        return "/auth/AuthPasswordChange.empty";
    }

    @RequestMapping(value = "/auth/actionPasswordChange.do")
    public String actionPasswordChange(
            HttpServletRequest request
            , LoginVO newLoginVO
            , ModelMap model
    ) throws Exception {
        // 로그인 세션 검사
        LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
        if (loginVO == null) {
            model.addAttribute("alertMessage", "세션이 만료되었습니다.");
            return "forward:/auth/login.do";
        }

        try {
            newLoginVO.setId(loginVO.getId());
            authService.chagePassword(newLoginVO);
        } catch (EgovBizException e) {
            model.addAttribute("alertMessage", e.getMessage());
            model.addAttribute("password", newLoginVO.getPassword());
            model.addAttribute("passwordNew", newLoginVO.getPasswordNew());
            model.addAttribute("passwordNew2", newLoginVO.getPasswordNew());
            return "forward:/auth/passwordChange.do";
        }

        return "redirect:/auth/login.do?code=approve";
    }
}
