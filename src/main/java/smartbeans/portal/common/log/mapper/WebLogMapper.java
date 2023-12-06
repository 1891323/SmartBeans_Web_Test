package smartbeans.portal.common.log.mapper;

import smartbeans.portal.common.log.vo.WebLogVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * file : CntnLogMapper.jsp
 * author kiboomhan
 * since 2023-05-30
 * version 1.0
 */
@Mapper("CntnLogMapper")
public interface WebLogMapper {

    void insertCntnLog(WebLogVO webLogVO);
}
