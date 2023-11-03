package smartbeans.portal.admin.faq.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springmodules.validation.commons.DefaultBeanValidator;
import smartbeans.portal.admin.faq.service.FaqAdminService;
import smartbeans.portal.admin.faq.service.FaqManagerVO;

import javax.servlet.http.HttpServletRequest;

/*
* 관리자 게시판 > FAQ 관리 페이지
* 이후 파일 기능 추가
* import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
* */
@Controller
public class FaqAdminAdminController {

    @Autowired
    private FaqAdminService faqAdminService;

    @Autowired
    private DefaultBeanValidator beanValidator;


    public String selectFaqList(@ModelAttribute("searchVO") FaqManagerVO searchVO,
                                ModelMap model,
                                HttpServletRequest request){


        return null;
    }







}
