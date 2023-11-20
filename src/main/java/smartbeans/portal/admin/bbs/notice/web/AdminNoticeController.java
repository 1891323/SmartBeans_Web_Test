package smartbeans.portal.admin.bbs.notice.web;

import org.egovframe.rte.fdl.cryptography.EgovEnvCryptoService;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import smartbeans.cmmn.ComDefaultVO;
import smartbeans.cmmn.service.EgovFileMngService;
import smartbeans.cmmn.service.EgovFileMngUtil;
import smartbeans.cmmn.service.FileVO;
import smartbeans.portal.admin.bbs.notice.service.AdminNoticeSerivce;
import smartbeans.portal.admin.bbs.notice.service.NoticeBoardVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import java.util.List;
import java.util.Map;




@Controller
@RequestMapping(value = "/admin/noti")

public class AdminNoticeController {
    private static final Logger logger = LoggerFactory.getLogger(AdminNoticeController.class);

    @Resource(name = "AnnouncementAdminService")
    private AdminNoticeSerivce announcementAdminSerivce;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;

    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;

    /** 암호화서비스 */
    private static EgovEnvCryptoService cryptoService;


    /**
     * notice 게시판 글목록 출력
     * @param searchVO
     * @param request
     * @param model
     * @return
     */
    @RequestMapping({
            "/Announcement.do",
            "/Reference.do",
            "/FAQ.do",
            "/QnA.do",
            "/Board.do",
            "/BeanFarmingManual.do"
    })
    public String selectAminNoticeBoardList(@ModelAttribute("searchVO") NoticeBoardVO searchVO,
                                            HttpServletRequest request,
                                            ModelMap model){

        searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        searchVO.setPageSize(propertiesService.getInt("pageSize"));

        String requestUri = request.getRequestURI();

        if (requestUri.endsWith("/Announcement.do")) {
            searchVO.setNoticeBoardSubType(1); // 공지사항
            request.setAttribute("pageTitle", "공지사항");
        } else if (requestUri.endsWith("/Reference.do")) {
            searchVO.setNoticeBoardSubType(2); // 게시판
            request.setAttribute("pageTitle", "자료실");
        } else if (requestUri.endsWith("/FAQ.do")) {
            searchVO.setNoticeBoardSubType(3); // FAQ
            request.setAttribute("pageTitle", "FAQ");
        } else if (requestUri.endsWith("/QnA.do")) {
            searchVO.setNoticeBoardSubType(4); // FAQ
            request.setAttribute("pageTitle", "QnA");
        } else if (requestUri.endsWith("/Board.do")) {
            searchVO.setNoticeBoardSubType(5); // 게시판
            request.setAttribute("pageTitle", "게시판");
        } else if (requestUri.endsWith("/BeanFarmingManual.do")) {
            // QnA 로직 처리
            searchVO.setNoticeBoardSubType(6); // 콩 재배 메뉴얼
            request.setAttribute("pageTitle", "콩 재배 메뉴얼");
        }

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

        model.addAttribute("boardList", boardList);
        model.addAttribute("noticeBoardSubType", searchVO.getNoticeBoardSubType());
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("totCnt", totCnt);

        return "admin/AdminNoticeBoardList.admin";
    }

    /**
     * 게시판 상세글 보기
     * @param noticeBoardVO
     * @param model
     * @return
     */
    @GetMapping(value = "/selectAdminDetailNoticeBoard.do")
    public String selectAdminDetailNoticeBoard(@ModelAttribute("searchVO") NoticeBoardVO noticeBoardVO, ModelMap model){

        NoticeBoardVO boardVO= announcementAdminSerivce.selectBoardDetail(noticeBoardVO);

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
            case 6:
                boardType = "Q&A";
                break;

            default:
                boardType = "콩 재배 메뉴얼";
                break;
        }

        model.addAttribute("boardVO", boardVO);
        model.addAttribute("boardType", boardType);

        return "admin/AdminNoticeBoardDetail.admin";
    }

    /**
     * Name: selectAdminEditNoticeBoard()
     * Descriptions: 관리자 공지사항 등록 페이지 이동
     * @param NoticeBoardVO
     * @param editmode
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/selectAdminEditNoticeBoard.do")
    public String selectAdminEditNoticeBoard(NoticeBoardVO boardVO, String editmode, ModelMap model) throws Exception {
        if ("U".equals(editmode)) {

            boardVO = announcementAdminSerivce.selectBoardDetail(boardVO);
            model.addAttribute("editmode", "U");
        }else {
            model.addAttribute("editmode", "I");
        }

        model.addAttribute("boardVO", boardVO);
        model.addAttribute("noticeBoardSubType", boardVO.getNoticeBoardSubType());


        return "admin/AdminNoticeBoardAdd.admin";
    }

    /**
     * 게시물 삽입 페이지
     * @param multiRequest
     * @param boardVO
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/insertAdminNoticeBoard.do")
    public String insertAdminNoticeBoard(final MultipartHttpServletRequest multiRequest, NoticeBoardVO boardVO, ModelMap model) throws Exception {
        List<FileVO> result = null;
        String atchFileId = "";

        //로그인 구현 전 임시로 setting
        boardVO.setNoticeWrtr("임시 작성자");
        boardVO.setMbrId("1");

        final Map<String, MultipartFile> files = multiRequest.getFileMap();
        if (!files.isEmpty()) {
            result = fileUtil.parseFileInf(files, "BBS_", 0, "", "");
            atchFileId = fileMngService.insertFileInfs(result);
            boardVO.setAtchFileId(atchFileId);
        }
        announcementAdminSerivce.insertBoard(boardVO);


        model.addAttribute("editmode", "I");
        model.addAttribute("boardVO", boardVO);

        // noticeBoardSubType 값에 따라 다른 페이지로 리디렉션
        String redirectUrl = "redirect:/admin/noti/";
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
            case 6:
                redirectUrl += "BeanFarmingManual.do";
                break;

            default:
                redirectUrl += "Announcement.do"; // 기본값 혹은 예외 처리
        }

        return redirectUrl;

    }


    @RequestMapping("AnnouncementList.do")
    public String selectAminNoticeBoardList(@ModelAttribute("searchVO")ComDefaultVO searchVO, ModelMap model){

        List<NoticeBoardVO> allNotices = announcementAdminSerivce.selectAll();


        /* pageing setting */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());


        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        model.addAttribute("allNotices", allNotices);
        int totalcnt = paginationInfo.getTotalRecordCount();
        model.addAttribute("totalcnt", totalcnt);




        return "admin/AnnouncementList.admin";
    }

    /**
     * Name: deleteAdminNoticeBoard()
     * Descriptions: 관리자 게시글 삭제
     * @param boardVO
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/deleteAdminNoticeBoard.do")
    public String deleteAdminNoticeBoard(NoticeBoardVO boardVO, ModelMap model) throws Exception {

        System.out.println("파일 삭제 컬럼 테스트중 컨트롤러 첨부아이디================"+boardVO.getAtchFileId());
        int result = announcementAdminSerivce.deleteBoard(boardVO);

        String redirectUrl = "redirect:/admin/noti/";
        switch (boardVO.getNoticeBoardSubType()) {
            case 1:
                redirectUrl += "Announcement.do";
                break;
            case 5:
                redirectUrl += "Board.do";
                break;
            case 4:
                redirectUrl += "QnA.do";
                break;
            default:
                redirectUrl += "Announcement.do"; // 기본값 혹은 예외 처리
        }

        return redirectUrl;

    }

    @PostMapping(value = "/updateAdminNoticeBoard.do")
    public String updateAdminNoticeBoard(final MultipartHttpServletRequest multiRequest, NoticeBoardVO boardVO, ModelMap model) throws Exception {
        boardVO.setDecryptAtchFileId(cryptoService);
        String atchFileId = boardVO.getAtchFileId();

        final List<MultipartFile> files = multiRequest.getFiles("file_1");

//        if (!files.isEmpty()) {
//            //파일이 없던 게시글에 파일을 추가할 때 - 신규 atchFileId생성
//            if (atchFileId == null || "".equals(atchFileId)) {
//                List<FileVO> result = fileUtil.parseFileInf(files, "BBS_", 0, atchFileId, "");
//                atchFileId = fileMngService.insertFileInfs(result);
//                boardVO.setAtchFileId(atchFileId);
//            } else {
//                FileVO fvo = new FileVO();
//                fvo.setAtchFileId(atchFileId);
//                int cnt = fileMngService.getMaxFileSN(fvo);
//                List<FileVO> _result = fileUtil.parseFileInf(files, "BBS_", cnt, atchFileId, "");
//                fileMngService.updateFileInfs(_result);
//            }
//        }


        announcementAdminSerivce.updateBoard(boardVO);

        boardVO = announcementAdminSerivce.selectBoardDetail(boardVO);

        model.addAttribute("boardVO", boardVO);
        return "admin/AdminNoticeBoardDetail.admin";
    }

    @PostMapping("/updateTopFixedStatus.do")
    public int updateTopFixedStatus(@RequestParam("noticeBoardNo") int noticeBoardNo
                                    ) {

        logger.info("변경 대상================"+noticeBoardNo);
        int result = announcementAdminSerivce.updateTopFixedStatus(noticeBoardNo);

        return result;
    }


}// end of class