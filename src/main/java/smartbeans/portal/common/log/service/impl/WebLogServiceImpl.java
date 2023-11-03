package smartbeans.portal.common.log.service.impl;

import smartbeans.portal.common.log.mapper.WebLogMapper;
import smartbeans.portal.common.log.service.WebLogService;
import smartbeans.portal.common.log.vo.WebLogVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * file : CntnLogServiceImpl.jsp
 * author kiboomhan
 * since 2023-05-30
 * version 1.0
 */
@Service("CntnLogService")
public class WebLogServiceImpl implements WebLogService {

    @Resource(name = "CntnLogMapper")
    private WebLogMapper webLogMapper;

    @Override
    public void insertCntnLog(WebLogVO webLogVO) throws Exception {
        webLogMapper.insertCntnLog(webLogVO);
    }
}
