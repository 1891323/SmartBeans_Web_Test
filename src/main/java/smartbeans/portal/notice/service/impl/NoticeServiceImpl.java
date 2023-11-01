package smartbeans.portal.notice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smartbeans.portal.notice.dao.NoticeDAO;
import smartbeans.portal.notice.service.NoticeService;
import smartbeans.portal.notice.vo.NoticeVO;

@Service("NoticeService")
public class NoticeServiceImpl implements NoticeService {

	@Autowired
    private NoticeDAO NoticeDAO;
	
	@Override
	public Map<String, Object> selectNoticeBoardList(NoticeVO NoticeVO) {
		List<?> result = NoticeDAO.selectNoticeBoardList(NoticeVO);
		int cnt = NoticeDAO.selectNoticeBoardListCnt(NoticeVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("resultList", result);
		map.put("resultCnt", Integer.toString(cnt));

		return map;
	}
	
	@Override
	public int insertNoticeBoardOne(NoticeVO NoticeVO) {
		int result = NoticeDAO.insertNoticeBoardOne(NoticeVO);
		return result;
	}
	
	@Override
	public NoticeVO selectNoticeBoardDetail(int no) {
		return NoticeDAO.selectNoticeBoardDetail(no);
	}
}