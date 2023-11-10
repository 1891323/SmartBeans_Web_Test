package smartbeans.portal.admin.bbs.notice.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smartbeans.cmmn.service.EgovFileMngService;
import smartbeans.portal.admin.bbs.notice.mapper.AdminNoticeMapper;
import smartbeans.portal.admin.bbs.notice.service.AdminNoticeSerivce;
import smartbeans.portal.admin.bbs.notice.service.NoticeBoardVO;

import javax.annotation.Resource;
import java.util.List;


@Service("AnnouncementAdminService")
public class AdminNoticeServiceImpl implements AdminNoticeSerivce {

    @Resource(name= "adminNoticeMapper")
    private AdminNoticeMapper announcementAdminMapper;

    @Resource(name = "EgovFileMngService")
    EgovFileMngService egovFileMngService;

    @Override
    public List<NoticeBoardVO> selectAll() {
        return announcementAdminMapper.selectAll();
    }

    @Override
    public List<NoticeBoardVO> selectBoardList(NoticeBoardVO searchVO) { return announcementAdminMapper.selectBoardList(searchVO); }

    @Override
    public int selectBoardListTotCnt(NoticeBoardVO searchVO) { return announcementAdminMapper.selectBoardListTotCnt(searchVO); }

    @Override
    public NoticeBoardVO selectBoardDetail(NoticeBoardVO noticeBoardVO) { return announcementAdminMapper.selectBoardDetail(noticeBoardVO);    }

    @Override
    public int  insertBoard(NoticeBoardVO boardVO) { return announcementAdminMapper.insertBoard(boardVO);   }

    @Transactional
    @Override
    public int deleteBoard(NoticeBoardVO boardVO) {

         int cnt = announcementAdminMapper.deleteBoard(boardVO);

         System.out.println("파일 삭제 컬럼 테스트중 첨부아이디================"+boardVO.getAtchFileId());

        // 첨부 파일이 있는 경우, 파일 테이블의 삭제 플래그도 업데이트
        if (boardVO.getAtchFileId() != null) {
            egovFileMngService.updateDeleteFlagForFiles(boardVO.getAtchFileId());
        }
        return 0;

    }

    @Override
    public void updateBoard(NoticeBoardVO boardVO) {     announcementAdminMapper.updateBoard(boardVO) ;  }


}
