package smartbeans.portal.user.notice.web;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import smartbeans.cmmn.ComDefaultVO;
import smartbeans.cmmn.service.EgovFileMngService;
import smartbeans.cmmn.service.EgovFileMngUtil;
import smartbeans.cmmn.service.FileVO;
import smartbeans.portal.admin.bbs.notice.service.NoticeBoardVO;
import smartbeans.portal.user.notice.service.UserNoticeService;
import smartbeans.portal.user.notice.service.UserNoticeVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import twitter4j.User;


import java.util.List;
import java.util.Map;

import static com.squareup.okhttp.internal.Internal.logger;

@Controller
@RequestMapping(value= "/user/noti")

public class UserNoticeController {

    private static final Logger logger = LoggerFactory.getLogger(UserNoticeController.class);

    @Resource(name = "UserNoticeService")
    private UserNoticeService userNoticeService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /**
     * notice 게시판 글목록 출력
     *
     * @param searchVO
     * @param request
     * @param model
     * @return
     */
    @RequestMapping({
            "/Announcement.do",
            "/Board.do",
            "/QnA.do",
            "/FAQ.do",
            "/Reference.do"
    })
    public String userNoticeView(@ModelAttribute("UserNoticeVO")
                                     UserNoticeVO searchVO,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 ModelMap model) throws Exception {

        searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        searchVO.setPageSize(propertiesService.getInt("pageSize"));

        String requestUri = request.getRequestURI();

        if (requestUri.endsWith("/Announcement.do")) {
            // 공지사항 로직 처리
            searchVO.setNoticeBoardType(4); // 알림마당
            searchVO.setNoticeBoardSubType(1); // 공지사항
            request.setAttribute("pageTitle", "공지사항");
        } else if (requestUri.endsWith("/Reference.do")) {
            // 게시판 로직 처리
            searchVO.setNoticeBoardType(4); // 알림마당
            searchVO.setNoticeBoardSubType(2); // 게시판
            request.setAttribute("pageTitle", "자료실");
        } else if (requestUri.endsWith("/FAQ.do")) {
            // 게시판 로직 처리
            searchVO.setNoticeBoardType(4); // 알림마당
            searchVO.setNoticeBoardSubType(3); // 게시판
            request.setAttribute("pageTitle", "FAQ");
        } else if (requestUri.endsWith("/QnA.do")) {
            // QnA 로직 처리
            searchVO.setNoticeBoardType(4); // 알림마당
            searchVO.setNoticeBoardSubType(4); // QnA
            request.setAttribute("pageTitle", "Q&A");
        } else if (requestUri.endsWith("/Board.do")) {
            // 게시판 로직 처리
            searchVO.setNoticeBoardType(4); // 알림마당
            searchVO.setNoticeBoardSubType(5); // 게시판
            request.setAttribute("pageTitle", "게시판");
        }

        /* pageing setting */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<UserNoticeVO> boardList = userNoticeService.selectUserBoardList(searchVO);

        int totCnt = userNoticeService.selectUserBoardListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute("boardList", boardList);
        model.addAttribute("noticeBoardSubType", searchVO.getNoticeBoardSubType());
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("totCnt", totCnt);

        return "user/notice/UserNotice.lnb"; // view 반환
    }

    /**
     * 게시판 상세글 보기
     *
     * @param userNoticeVO
     * @param model
     * @return
     */

    @GetMapping(value = "/selectUserDetailNoticeBoard.do")
    public String selectUserDetailNoticeBoard(@ModelAttribute("searchVO") UserNoticeVO userNoticeVO, ModelMap model) {

        UserNoticeVO boardVO = userNoticeService.selectUserBoardDetail(userNoticeVO);

        String boardType = "";

        switch (boardVO.getNoticeBoardSubType()) {
            case 1:
                boardType = "공지사항";
                break;
            case 2:
                boardType = "자료실";
                break;
            case 3:
                boardType = "FAQ";
                break;
            case 4:
                boardType = "Q&A";
                break;
            case 5:
                boardType = "게시판";
                break;

            default:
                boardType = "공지사항";
                break;
        }
        model.addAttribute("boardVO", boardVO);
        model.addAttribute("boardType", boardType);

        return "user/notice/UserNoticeDetail.lnb";
    }
}
