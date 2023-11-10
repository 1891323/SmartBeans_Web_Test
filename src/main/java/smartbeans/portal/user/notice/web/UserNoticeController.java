package smartbeans.portal.user.notice.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import smartbeans.portal.user.notice.service.UserNoticeService;
import smartbeans.portal.user.notice.service.UserNoticeVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserNoticeController {

    @Resource(name = "userNoticeService")
    private UserNoticeService usernoticeService;

    @RequestMapping(value = "user/noti/Announcement.do")
    public String userNoticeView(@ModelAttribute("userNoticeVO") UserNoticeVO userNoticeVO
            , HttpServletRequest request
            , HttpServletResponse response
            , ModelMap model) throws Exception {

        return "user/notice/UserNotice.lnb"; // view 반환
    }
}
