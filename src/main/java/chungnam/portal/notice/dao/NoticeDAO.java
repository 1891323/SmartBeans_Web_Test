package chungnam.portal.notice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import chungnam.portal.notice.vo.NoticeVO;

@Mapper
public interface NoticeDAO {
	public List<?> selectNoticeBoardList(NoticeVO NoticeVO);
	public int selectNoticeBoardListCnt(NoticeVO NoticeVO);
	public int insertNoticeBoardOne(NoticeVO NoticeVO);
	public NoticeVO selectNoticeBoardDetail(int no);
}