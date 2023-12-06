package smartbeans.portal.admin.bbs.notice.mapper;

import org.apache.ibatis.annotations.Mapper;
import smartbeans.portal.admin.bbs.notice.service.NoticeBoardVO;

import java.util.List;

@Mapper
public interface AdminNoticeMapper {
    public List<NoticeBoardVO> selectAll();

    public List<NoticeBoardVO> selectBoardList(NoticeBoardVO searchVO);

    int selectBoardListTotCnt(NoticeBoardVO searchVO);

    NoticeBoardVO selectBoardDetail(NoticeBoardVO noticeBoardVO);


    int insertBoard(NoticeBoardVO boardVO);

    int deleteBoard(NoticeBoardVO boardVO);

    void updateBoard(NoticeBoardVO boardVO);

    int updateTopFixedStatus(int noticeBoardNo);
}