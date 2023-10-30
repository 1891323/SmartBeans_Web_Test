package smartbeans.portal.admin.announcement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartbeans.portal.admin.announcement.mapper.AnnouncementAdminMapper;
import smartbeans.portal.admin.announcement.service.AnnouncementAdminSerivce;
import smartbeans.portal.admin.announcement.service.NoticeBoardVO;

import java.util.List;


@Service
public class AnnouncementAdminServiceImpl implements AnnouncementAdminSerivce {

    @Autowired
    private AnnouncementAdminMapper announcementAdminMapper;
    @Override
    public List<NoticeBoardVO> selectAll() {
        System.out.println("목록출력 테스트중 서비스 ===============================================");
        return announcementAdminMapper.selectAll();
    }
}
