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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import smartbeans.cmmn.ComDefaultVO;
import smartbeans.cmmn.service.EgovFileMngService;
import smartbeans.cmmn.service.EgovFileMngUtil;
import smartbeans.cmmn.service.FileVO;
import smartbeans.portal.admin.announcement.service.AnnouncementAdminSerivce;
import smartbeans.portal.admin.announcement.service.NoticeBoardVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.util.List;
import java.util.Map;

import static com.squareup.okhttp.internal.Internal.logger;


@Controller
@RequestMapping(value = "/admin/noti")
public class AnnouncementAdminController {

    private static final Logger logger = LoggerFactory.getLogger(AnnouncementAdminController.class);

    @Resource(name = "AnnouncementAdminService")
    private AnnouncementAdminSerivce announcementAdminSerivce;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;

    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileMngService;

    /**
     * notice 게시판 글목록 출력
     * @param searchVO
     * @param request
     * @param model
     * @return
     */
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



        return "admin/AdminNoticeBoardList.admin";
    }

    /**
     * 상세글 이동
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

    /**
     * Name: selectAdminEditNoticeBoard()
     * Descriptions: 관리자 공지사항 등록 페이지 이동
     * @param noticeBoardVO
     * @param editmode
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/selectAdminEditNoticeBoard.do")
    public String selectAdminEditNoticeBoard(NoticeBoardVO boardVO, String editmode, ModelMap model) throws Exception {
        if ("U".equals(editmode)) {
//            boardVO = announcementAdminSerivce.selectBoard(boardVO);
//            model.addAttribute("boardVO", boardVO);
        }

        model.addAttribute("editmode", editmode);
        model.addAttribute("noticeBoardVO", boardVO);
        model.addAttribute("noticeBoardSubType", boardVO.getNoticeBoardSubType());

        return "admin/AdminNoticeBoardAdd.admin";
    }

    @PostMapping(value = "/insertAdminNoticeBoard.do")
    public String insertAdminNoticeBoard(final MultipartHttpServletRequest multiRequest, NoticeBoardVO boardVO, ModelMap model) throws Exception {
        List<FileVO> result = null;
        String atchFileId = "";
        boardVO.setNoticeBoardType(4);

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

}