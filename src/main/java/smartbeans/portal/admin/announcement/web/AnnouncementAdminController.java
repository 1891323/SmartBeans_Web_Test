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
import javax.servlet.http.HttpServletRequest;


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

    @RequestMapping({
            "/Announcement.do",
            "/Board.do",
            "/QnA.do",
    })
    public String selectAminNoticeBoardList(@ModelAttribute("searchVO") NoticeBoardVO searchVO,  HttpServletRequest request, ModelMap model){

        searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        searchVO.setPageSize(propertiesService.getInt("pageSize"));

        String requestUri = request.getRequestURI();


        if (requestUri.endsWith("/Announcement.do")) {
            // 공지사항 로직 처리
            searchVO.setNoticeBoardType(4); // 알림마당
            searchVO.setNoticeBoardSubType(1); // 공지사항
            request.setAttribute("pageTitle", "공지사항");
        } else if (requestUri.endsWith("/Board.do")) {
            // 게시판 로직 처리
            searchVO.setNoticeBoardType(4); // 알림마당
            searchVO.setNoticeBoardSubType(5); // 게시판
            request.setAttribute("pageTitle", "게시판");
        } else if (requestUri.endsWith("/QnA.do")) {
            // QnA 로직 처리
            searchVO.setNoticeBoardType(4); // 알림마당
            searchVO.setNoticeBoardSubType(4); // QnA
            request.setAttribute("pageTitle", "Q&A");
        }


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

//        logger.info("공지 사항 목록 테스트 중=========================Board List: {}", boardList);

        model.addAttribute("boardList", boardList);

        model.addAttribute("noticeBoardSubType", searchVO.getNoticeBoardSubType());

        model.addAttribute("paginationInfo", paginationInfo);



        return "admin/AdminNoticeBoardList.admin";
    }

    @GetMapping(value = "/selectAdminDetailNoticeBoard.do")
    public String selectAdminDetailNoticeBoard(@ModelAttribute("searchVO") NoticeBoardVO noticeBoardVO, ModelMap model){

        NoticeBoardVO boardVO= announcementAdminSerivce.selectBoardDetail(noticeBoardVO);

        String boardType = "";

        switch (boardVO.getNoticeBoardSubType()) {
            case 1:
                boardType = "공지사항";
                break;
            case 5:
                boardType = "게시판";
                break;
            case 4:
                boardType = "Q&A";
                break;
            default:
                boardType = "기타";
                break;
        }

        model.addAttribute("boardVO", boardVO);
        model.addAttribute("boardType", boardType);

        return "admin/AdminNoticeBoardDetail.admin";
    }


}
