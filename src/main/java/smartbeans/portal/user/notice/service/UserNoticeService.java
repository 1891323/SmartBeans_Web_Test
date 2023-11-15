package smartbeans.portal.user.notice.service;


import java.util.List;
public interface UserNoticeService {

    List<UserNoticeVO> selectALL();

    List<UserNoticeVO> selectBoardList(UserNoticeVO searchVO);

    int selectBoardListTotCnt(UserNoticeVO searchVO);

}
