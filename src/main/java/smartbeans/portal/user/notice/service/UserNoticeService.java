package smartbeans.portal.user.notice.service;

import java.util.List;

public interface UserNoticeService {

    List<UserNoticeVO> selectALL();

    List<UserNoticeVO> selectUserBoardList(UserNoticeVO searchVO);

    int selectUserBoardListTotCnt(UserNoticeVO searchVO);

    UserNoticeVO selectUserBoardDetail(UserNoticeVO userNoticeVO);

}
