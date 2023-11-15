package smartbeans.portal.user.notice.mapper;

import org.apache.ibatis.annotations.Mapper;
import smartbeans.portal.admin.bbs.notice.service.NoticeBoardVO;
import smartbeans.portal.user.notice.service.UserNoticeVO;

import java.util.List;

@Mapper
public interface UserNoticeMapper {
    public List<UserNoticeVO> selectAll();

    public List<UserNoticeVO> selectBoardList(UserNoticeVO searchVO);

    int selectBoardListTotCnt(UserNoticeVO searchVO);

    UserNoticeVO selectBoardDetail(UserNoticeVO userNoticeVO);

    int insertBoard(UserNoticeVO boardVO);
}
