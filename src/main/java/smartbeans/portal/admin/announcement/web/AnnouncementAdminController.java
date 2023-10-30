package smartbeans.portal.admin.announcement.web;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import smartbeans.cmmn.ComDefaultVO;
import smartbeans.portal.admin.announcement.service.AnnouncementAdminSerivce;
import smartbeans.portal.admin.announcement.service.NoticeBoardVO;

import javax.annotation.Resource;

import java.util.List;

import static com.squareup.okhttp.internal.Internal.logger;


@Controller
public class AnnouncementAdminController {

    @Autowired
    private AnnouncementAdminSerivce announcementAdminSerivce;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @GetMapping("AnnouncementList.do")
    public String selectAminNoticeBoardList(@ModelAttribute("searchVO")ComDefaultVO searchVO, ModelMap model){

        System.out.println("목록출력 테스트중 ===============================================");
        List<NoticeBoardVO> allNotices = announcementAdminSerivce.selectAll();
        model.addAttribute("allNotices", allNotices);
        logger.info("All notices fetched: " + allNotices);


        return "admin/AnnouncementList.index";
    }



}
