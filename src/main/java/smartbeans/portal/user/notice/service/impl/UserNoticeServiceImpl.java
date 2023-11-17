package smartbeans.portal.user.notice.service.impl;

import org.springframework.transaction.annotation.Transactional;
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
    public UserNoticeVO UserNoticeDetail(UserNoticeVO userNoticeVO) {
        return usernoticeMapper.UserNoticeDetail(userNoticeVO);    }
}
