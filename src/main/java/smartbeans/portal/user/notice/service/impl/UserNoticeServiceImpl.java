package smartbeans.portal.user.notice.service.impl;

import org.springframework.stereotype.Service;
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
    public  List<UserNoticeVO> selectCmntList(UserNoticeVO searchVO){
        return usernoticeMapper.selectCmntList(searchVO);
    }

    @Override
    public UserNoticeVO selectCmntDetail(int cmntNo) {
        return usernoticeMapper.selectCmntDetail(cmntNo);
    }

    public int userinsertComment(UserNoticeVO cmntVO) {
        try {
            usernoticeMapper.userinsertComment(cmntVO);

            return 1; // 성공
        } catch (Exception e) {
            return 0; // 실패
        }
    }
    @Override
    public int userupdateComment(UserNoticeVO cmntVO) {
        return usernoticeMapper.userupdateComment(cmntVO);
    }

    @Override
    public int userdeleteComment(int cmntNo) {
        return usernoticeMapper.userdeleteComment(cmntNo);
    }

}
