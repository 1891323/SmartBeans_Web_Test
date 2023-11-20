package smartbeans.portal.user.notice.mapper;

import org.apache.ibatis.annotations.Mapper;
import smartbeans.portal.user.notice.service.UserNoticeVO;

import java.util.List;

@Mapper
public interface UserNoticeMapper {
    public List<UserNoticeVO> selectAll();

    public List<UserNoticeVO> selectUserBoardList(UserNoticeVO searchVO);


    int selectUserBoardListTotCnt(UserNoticeVO searchVO);

    UserNoticeVO selectUserBoardDetail(UserNoticeVO userNoticeVO);

}
