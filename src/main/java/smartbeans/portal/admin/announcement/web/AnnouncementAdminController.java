package smartbeans.portal.admin.announcement.web;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import smartbeans.cmmn.ComDefaultVO;
import smartbeans.portal.admin.announcement.service.AnnouncementAdminSerivce;
import smartbeans.portal.admin.announcement.service.NoticeBoardVO;

import javax.annotation.Resource;
import javax.xml.ws.RequestWrapper;

import java.util.List;

import static com.squareup.okhttp.internal.Internal.logger;


@Controller
public class AnnouncementAdminController {

    @Resource(name = "AnnouncementAdminService")
    private AnnouncementAdminSerivce announcementAdminSerivce;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    //밑에 URL을 가져와서 http://localhost:9090/AnnouncementList.do 라고 검색하면 접속 가능함!
    @RequestMapping("AnnouncementList.do")
    public String selectAminNoticeBoardList(@ModelAttribute("searchVO")ComDefaultVO searchVO, ModelMap model){

        System.out.println("목록출력 테스트중 ===============================================");
        List<NoticeBoardVO> allNotices = announcementAdminSerivce.selectAll();
        model.addAttribute("allNotices", allNotices);
        logger.info("공지목록리스트 출력 테스트: " + allNotices);

        /* pageing setting */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());



        return "admin/AnnouncementList.admin";
    }



}