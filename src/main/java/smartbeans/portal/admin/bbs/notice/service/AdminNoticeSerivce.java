package smartbeans.portal.admin.bbs.notice.service;


import java.util.List;

public interface AdminNoticeSerivce {

    List<NoticeBoardVO> selectAll();


    List<NoticeBoardVO> selectBoardList(NoticeBoardVO searchVO);

    int selectBoardListTotCnt(NoticeBoardVO searchVO);

    NoticeBoardVO selectBoardDetail(NoticeBoardVO noticeBoardVO);

    int insertBoard(NoticeBoardVO boardVO);

    int deleteBoard(NoticeBoardVO boardVO);

    void updateBoard(NoticeBoardVO boardVO);

    int updateTopFixedStatus(int noticeBoardNo);
}
