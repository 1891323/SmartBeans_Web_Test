package smartbeans.portal.admin.announcement.web;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value = "/admin/noti")
public class AnnouncementAdminController {

    private static final Logger logger = LoggerFactory.getLogger(AnnouncementAdminController.class);

    @Resource(name = "AnnouncementAdminService")
    private AnnouncementAdminSerivce announcementAdminSerivce;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @RequestMapping("/AnnouncementList.do")
    public String selectAminNoticeBoardList(@org.jetbrains.annotations.NotNull @ModelAttribute("searchVO") NoticeBoardVO searchVO, @NotNull ModelMap model){

        searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        searchVO.setPageSize(propertiesService.getInt("pageSize"));

        // 게시판 타입과 하위 타입 세팅
        searchVO.setNoticeBoardType(4); // 알림마당
        searchVO.setNoticeBoardSubType(1); // 공지사항

        System.out.println(" 공지 사항 목록 테스트 중  ===============================================");
        System.out.println(" getter getNoticeBoardType~~  ===============================================" + searchVO.getNoticeBoardType());
        System.out.println(" getter getNoticeBoardSubType~~  ===============================================" + searchVO.getNoticeBoardSubType());

        /* pageing setting */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());


        List<NoticeBoardVO> boardList = announcementAdminSerivce.selectBoardList(searchVO);

        int totCnt = announcementAdminSerivce.selectBoardListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);

        logger.info("=========================Board List: {}", boardList);
        System.out.println(" 공지 사항 목록 테스트 중  ===============================================" +boardList);

        model.addAttribute("boardList", boardList);
        model.addAttribute("paginationInfo", paginationInfo);



        return "admin/AnnouncementList.admin";
    }



}
