package smartbeans.portal.common.log.service;

import smartbeans.portal.common.log.vo.WebLogVO;

/**
 * file : CntnLogService.jsp
 * author kiboomhan
 * since 2023-05-30
 * version 1.0
 */
public interface WebLogService {

    /**
     * 접속로그 등록
     * @param webLogVO
     * @throws Exception
     */
    void insertCntnLog(WebLogVO webLogVO) throws Exception;
}
