package smartbeans.portal.user.notice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartbeans.portal.user.notice.service.UserNoticeService;
import smartbeans.portal.user.notice.service.UserNoticeVO;
import smartbeans.portal.user.notice.mapper.UserNoticeMapper;


import javax.annotation.Resource;
import java.util.List;

@Service("UserNoticeService")
public class UserNoticeServiceImpl implements UserNoticeService {

    @Resource(name="userNoticeMapper")
    private UserNoticeMapper usernoticeMapper;

    @Override
    public List<UserNoticeVO> selectALL() {
        return usernoticeMapper.selectAll();
    }

    @Override
    public List<UserNoticeVO> selectBoardList(UserNoticeVO searchVO) {
        return usernoticeMapper.selectBoardList(searchVO);
    }

    @Override
    public int selectBoardListTotCnt(UserNoticeVO searchVO) {
        return usernoticeMapper.selectBoardListTotCnt(searchVO);
    }
}
