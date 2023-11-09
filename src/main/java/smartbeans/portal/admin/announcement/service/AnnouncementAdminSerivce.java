package smartbeans.portal.admin.announcement.service;


import java.util.List;

public interface AnnouncementAdminSerivce {

    List<NoticeBoardVO> selectAll();


    List<NoticeBoardVO> selectBoardList(NoticeBoardVO searchVO);

    int selectBoardListTotCnt(NoticeBoardVO searchVO);

    NoticeBoardVO selectBoardDetail(NoticeBoardVO noticeBoardVO);

    int insertBoard(NoticeBoardVO boardVO);
}
