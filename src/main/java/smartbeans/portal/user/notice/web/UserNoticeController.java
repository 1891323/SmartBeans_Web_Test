package smartbeans.portal.user.notice.web;

import org.egovframe.rte.fdl.cryptography.EgovEnvCryptoService;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
import smartbeans.portal.user.notice.service.UserNoticeService;
import smartbeans.portal.user.notice.service.UserNoticeVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @PostMapping("/userupdateTopFixedStatus.do")
    public int updateTopFixedStatus(@RequestParam("noticeBoardNo") int noticeBoardNo
    ) {

        logger.info("변경 대상================"+noticeBoardNo);
        int result = userNoticeService.userupdateTopFixedStatus(noticeBoardNo);

        return result;
    }

    /* 댓글 기능 에러 발생중
    *  userboard.xml은 문제없이 쿼리 선언 완료하였으므로
    *  controller 및 service 수정해볼 것 */
    /**
     * 댓글 목록 조회
     * @param searchVO
     * @param model
     * @return
     */
    @GetMapping(value = "/selectCmntList.do")
    public String selectCmntList(@ModelAttribute("searchVO") UserNoticeVO searchVO, ModelMap model) {
        List<UserNoticeVO> cmntList = userNoticeService.selectCmntList(searchVO);
        model.addAttribute("cmntList", cmntList);
        return "user/notice/UserCmntList.lnb";
    }

    /**
     * 댓글 상세 조회
     * @param cmntNo
     * @param model
     * @return
     */
    @GetMapping(value = "/selectCmntDetail.do")
    public String selectCmntDetail(@RequestParam("cmntNo") int cmntNo, ModelMap model) {
        UserNoticeVO cmntVO = userNoticeService.selectCmntDetail(cmntNo);
        model.addAttribute("cmntVO", cmntVO);
        return "user/notice/UserCmntDetail.lnb";
    }

    /**
     * 댓글 삽입
     * @param cmntVO
     * @param model
     * @return
     */
    @PostMapping(value = "/insertUserComment.do")
    @ResponseBody
    public String insertUserComment(UserNoticeVO cmntVO, ModelMap model) {
        userNoticeService.userinsertComment(cmntVO);
        return "redirect:/user/noti/selectCmntList.do";
    }

    /**
     * 댓글 수정
     * @param cmntVO
     * @param model
     * @return
     */
    @PostMapping(value = "/updateUserComment.do")
    public String updateUserComment(UserNoticeVO cmntVO, ModelMap model) {
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
    public String deleteUserComment(@RequestParam("cmntNo") int cmntNo, ModelMap model) {
        userNoticeService.userdeleteComment(cmntNo);
        return "redirect:/user/noti/selectCmntList.do";
    }

}