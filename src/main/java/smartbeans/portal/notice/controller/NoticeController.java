package smartbeans.portal.notice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import smartbeans.portal.notice.service.NoticeService;
import smartbeans.portal.notice.vo.NoticeVO;

@Controller
public class NoticeController {

	@Autowired
    private NoticeService NoticeService;
    
	//20230510 - sbk EgovComIndexController.java에 선언되어있음
    @RequestMapping("/NoticeBoardList.do")
    public String NoticeBoardList(@ModelAttribute("noticeVO") NoticeVO NoticeVO, ModelMap model) throws Exception {
		Map<String, Object> map = NoticeService.selectNoticeBoardList(NoticeVO);
		//int totCnt = Integer.parseInt((String)map.get("resultCnt"));
		
		model.addAttribute("resultList", map.get("resultList"));
		model.addAttribute("resultCnt", map.get("resultCnt"));

		return "main/contents/admin/Board/Notice/List.index";
    }
	
	//20230511 - sbk 등록페이지
    @RequestMapping("/insertNoticeBoardPage.do")
    public String insertNoticeBoardPage() throws Exception {
		return "main/contents/admin/Board/Notice/Add.index";
    }
	
    //20230511 - sbk 등록페이지 등록버튼 구현
    @RequestMapping("/insertNoticeBoardOne.do")
    public String insertNoticeBoardOne(NoticeVO NoticeVO, ModelMap model) throws Exception {
		System.out.println("제목: " + NoticeVO.getSj());
		System.out.println("작성자: " + NoticeVO.getCreate_id());
		System.out.println("내용: " + NoticeVO.getCn());
		NoticeVO.setNtt_se("1");
		NoticeVO.setUpper_bbs_sn(0);
		NoticeVO.setUser_id("test");

		int result = NoticeService.insertNoticeBoardOne(NoticeVO);
		
		return "redirect:NoticeBoardList.do";
    }
	
	//20230511 - sbk 상세페이지
    @RequestMapping("/detailNoticeBoardPage.do")
    public String detailNoticeBoardPage(int no, ModelMap model) throws Exception {
    	NoticeVO NoticeVO = new NoticeVO();
    	NoticeVO = NoticeService.selectNoticeBoardDetail(no);
    	
    	System.out.println("@@@@@@@@@@@@ VO: " + NoticeVO);
    	
		model.addAttribute("bbs_sn", NoticeVO.getBbs_sn());
		model.addAttribute("sj", NoticeVO.getSj());
		model.addAttribute("create_id", NoticeVO.getBbs_sn());
		model.addAttribute("reg_dttm", NoticeVO.getReg_dttm());
		model.addAttribute("cn", NoticeVO.getCn());
    	
		return "main/contents/admin/Board/Notice/Detail.index";
    }
}