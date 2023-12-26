package smartbeans.portal.user.notice.web;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import smartbeans.cmmn.service.EgovFileMngService;
import smartbeans.cmmn.service.EgovFileMngUtil;
import smartbeans.cmmn.service.FileVO;
import smartbeans.portal.user.notice.service.UserNoticeService;
import smartbeans.portal.user.notice.service.UserNoticeVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value= "/user/noti")

public class UserNoticeController {
    private static final Logger logger = LoggerFactory.getLogger(UserNoticeController.class);

    @Resource(name = "UserNoticeService")
    private UserNoticeService userNoticeService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;

    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;

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
    public String selectUserNoticeBoardList(@ModelAttribute("UserNoticeVO")
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
            searchVO.setNoticeBoardSubType(2); // 자료실
            request.setAttribute("pageTitle", "자료실");
        } else if (requestUri.endsWith("/FAQ.do")) {
            // 게시판 로직 처리
            searchVO.setNoticeBoardType(4); // 알림마당
            searchVO.setNoticeBoardSubType(3); // FAQ
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

        if (searchVO.getNoticeBoardSubType() == 2) {
            return "user/notice/UserReference.lnb"; // NoticeBoardType이 2일 때 반환
        } else {
            return "user/notice/UserNotice.lnb"; // NoticeBoardType이 2가 아닌 경우 반환
        }
    }

    /**
     * 게시판 상세글 보기
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

    /**
     * Name: selectUserEditNoticeBoard()
     * Descriptions: 관리자 공지사항 등록 페이지 이동
     *
     * @param boardVO
     * @param editmode
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/selectUserEditNoticeBoard.do")
    public String selectUserEditNoticeBoard(UserNoticeVO boardVO, String editmode, ModelMap model) throws Exception {
        if ("U".equals(editmode)) {

            boardVO = userNoticeService.selectUserBoardDetail(boardVO);
            model.addAttribute("editmode", "U");
        } else {
            model.addAttribute("editmode", "I");
        }

        model.addAttribute("boardVO", boardVO);
        model.addAttribute("noticeBoardSubType", boardVO.getNoticeBoardSubType());


        return "user/notice/UserNoticeAdd.lnb";
    }

    /**
     * 게시물 삽입 페이지
     * @param multiRequest
     * @param boardVO
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/insertUserNoticeBoard.do")
    public String insertUserNoticeBoard(final MultipartHttpServletRequest multiRequest, UserNoticeVO boardVO, ModelMap model) throws Exception {
        List<FileVO> result = null;
        String atchFileId = "";

        //로그인 구현 전 임시로 setting
        boardVO.setNoticeWrtr("임시 회원");
        boardVO.setMbrId("2");

        final Map<String, MultipartFile> files = multiRequest.getFileMap();
        if (!files.isEmpty()) {
            result = fileUtil.parseFileInf(files, "BBS_", 0, "", "");
            atchFileId = fileMngService.insertFileInfs(result);
            boardVO.setAtchFileId(atchFileId);
        }
        userNoticeService.userinsertBoard(boardVO);


        model.addAttribute("editmode", "I");
        model.addAttribute("boardVO", boardVO);

        // noticeBoardSubType 값에 따라 다른 페이지로 리디렉션
        String redirectUrl = "redirect:/user/noti/";
        switch (boardVO.getNoticeBoardSubType()) {
            case 1:
                redirectUrl += "Announcement.do";
                break;
            case 2:
                redirectUrl += "Reference.do";
                break;
            case 3:
                redirectUrl += "FAQ.do";
                break;
            case 4:
                redirectUrl += "QnA.do";
                break;
            case 5:
                redirectUrl += "Board.do";
                break;
        }

        return redirectUrl;

    }
    //공지사항 상단 고정 기능
    @PostMapping("/userupdateTopFixedStatus.do")
    public int updateTopFixedStatus(@RequestParam("noticeBoardNo") int noticeBoardNo
    ) {

        logger.info("변경 대상================"+noticeBoardNo);
        int result = userNoticeService.userupdateTopFixedStatus(noticeBoardNo);

        return result;
    }

    /*  controller 및 service 수정 해볼 것 2023-12-12/jeoung */
    /**
     * 댓글 목록 조회
     * @param cmntVO
     * @return
     */
    @GetMapping(value = "/selectCmntList.do")
    @ResponseBody
    public List<UserNoticeVO> selectCmntList(@ModelAttribute("cmntVO") UserNoticeVO cmntVO) {
        List<UserNoticeVO> cmntList = userNoticeService.selectCmntList(cmntVO);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cmntList", cmntList);
        modelAndView.setViewName("user/notice/UserNoticeDetail");
        return cmntList;
    }

    /**
     * 댓글 삽입
     * @param cmntVO
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/insertUserComment.do")
    @ResponseBody
    public List<UserNoticeVO> userinsertComment(UserNoticeVO cmntVO, ModelMap model) {

        UserNoticeVO boardVO = new UserNoticeVO();
        boardVO.setNoticeWrtr("임시 회원");
        cmntVO.setMbrId("2");

        Date LocalDateTime = new Date();
        cmntVO.setCmntFirstRegistDtm(LocalDateTime);

        userNoticeService.userinsertComment(cmntVO);

        List<UserNoticeVO> cmntList = userNoticeService.selectCmntList(cmntVO);

        model.addAttribute("cmntVO", cmntVO);

        // 화면에 댓글 목록 전달
        return cmntList;
    }

    /**
     * 댓글 수정
     * @param cmntVO
     * @param model
     * @return
     */
    @PostMapping(value = "/updateUserComment.do")
    public String userupdateComment(UserNoticeVO cmntVO, ModelMap model) {
        userNoticeService.userupdateComment(cmntVO);
        return "redirect:/user/noti/selectCmntList.do";
    }

    /**
     * 댓글 삭제
     * @param cmntNo
     * @param model
     * @return
     */
    @GetMapping(value = "/deleteUserComment.do")
    public String userdeleteComment(@RequestParam("cmntNo") int cmntNo, ModelMap model) {
        userNoticeService.userdeleteComment(cmntNo);
        return "redirect:/user/noti/selectCmntList.do";
    }

}