package smartbeans.portal.user.notice.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smartbeans.cmmn.service.EgovFileMngService;
import smartbeans.portal.user.notice.mapper.UserNoticeMapper;
import smartbeans.portal.user.notice.service.UserNoticeService;
import smartbeans.portal.user.notice.service.UserNoticeVO;

import javax.annotation.Resource;
import java.util.List;

@Service("UserNoticeService")
public class UserNoticeServiceImpl implements UserNoticeService {

    @Resource(name="userNoticeMapper")
    private UserNoticeMapper usernoticeMapper;

    @Resource(name = "EgovFileMngService")
    EgovFileMngService egovFileMngService;

    @Override
    public List<UserNoticeVO> selectALL() {
        return usernoticeMapper.selectAll();
    }

    @Override
    public List<UserNoticeVO> selectUserBoardList(UserNoticeVO searchVO) {
        return usernoticeMapper.selectUserBoardList(searchVO);
    }

    @Override
    public int selectUserBoardListTotCnt(UserNoticeVO searchVO) {
        return usernoticeMapper.selectUserBoardListTotCnt(searchVO);
    }

    @Override
    public UserNoticeVO selectUserBoardDetail(UserNoticeVO userNoticeVO) {
        return usernoticeMapper.selectUserBoardDetail(userNoticeVO);
    }

    @Override
    public int userinsertBoard(UserNoticeVO boardVO){
        return usernoticeMapper.userinsertBoard(boardVO);
    }

    @Override
    public int userupdateTopFixedStatus(int noticeBoardNo) { return usernoticeMapper.userupdateTopFixedStatus(noticeBoardNo);   }

    @Override
    public UserNoticeVO UserQnaComment(UserNoticeVO userNoticeVO){
        return usernoticeMapper.UserQnaComment(userNoticeVO);
    }
}
