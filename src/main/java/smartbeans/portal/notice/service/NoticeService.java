package smartbeans.portal.notice.service;

import java.util.Map;

import smartbeans.portal.notice.vo.NoticeVO;

public interface NoticeService {
	Map<String, Object> selectNoticeBoardList(NoticeVO NoticeVO);
	int insertNoticeBoardOne(NoticeVO NoticeVO);
	NoticeVO selectNoticeBoardDetail(int no);
}