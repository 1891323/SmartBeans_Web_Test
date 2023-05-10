package chungnam.portal.AdminNoticeBoard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ALMList {

    // 공지사항 목록
	@RequestMapping("/AnnouncementList.do")
	public String AnnouncementList(ModelMap model) throws Exception{
		return "main/AdminNoticeBoard/AnnouncementList";
	}
	
	 

}
