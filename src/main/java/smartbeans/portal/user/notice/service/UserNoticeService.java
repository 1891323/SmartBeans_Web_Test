package smartbeans.portal.user.notice.service;

import java.util.List;

public interface UserNoticeService {

    List<UserNoticeVO> selectALL();

    List<UserNoticeVO> selectUserBoardList(UserNoticeVO searchVO);


    int selectUserBoardListTotCnt(UserNoticeVO searchVO);

    UserNoticeVO selectUserBoardDetail(UserNoticeVO userNoticeVO);

    int userinsertBoard(UserNoticeVO boardVO);

    int userupdateTopFixedStatus(int noticeBoardNo);

    List<UserNoticeVO> selectCmntList(UserNoticeVO cmntVO);

    UserNoticeVO selectCmntDetail(int cmntNo);

    int userinsertComment(UserNoticeVO cmntVO);

    int userupdateComment(UserNoticeVO cmntVO);

    int userdeleteComment(int cmntNo);
}
