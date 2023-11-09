package smartbeans.portal.admin.announcement.mapper;

import org.apache.ibatis.annotations.Mapper;
import smartbeans.portal.admin.announcement.service.NoticeBoardVO;

import java.util.List;

@Mapper
public interface AnnouncementAdminMapper {
    public List<NoticeBoardVO> selectAll();


    public List<NoticeBoardVO> selectBoardList(NoticeBoardVO searchVO);

    int selectBoardListTotCnt(NoticeBoardVO searchVO);

    NoticeBoardVO selectBoardDetail(NoticeBoardVO noticeBoardVO);


    int insertBoard(NoticeBoardVO boardVO);
}