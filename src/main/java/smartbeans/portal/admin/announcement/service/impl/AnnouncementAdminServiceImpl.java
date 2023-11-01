package smartbeans.portal.admin.announcement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartbeans.portal.admin.announcement.mapper.AnnouncementAdminMapper;
import smartbeans.portal.admin.announcement.service.AnnouncementAdminSerivce;
import smartbeans.portal.admin.announcement.service.NoticeBoardVO;

import javax.annotation.Resource;
import java.util.List;


@Service("AnnouncementAdminService")
public class AnnouncementAdminServiceImpl implements AnnouncementAdminSerivce {

    @Resource(name="announcementAdminMapper")
    private AnnouncementAdminMapper announcementAdminMapper;
    @Override
    public List<NoticeBoardVO> selectAll() {
        return announcementAdminMapper.selectAll();
    }


}