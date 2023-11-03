package smartbeans.portal.common.satsfc.mapper;

import smartbeans.portal.common.satsfc.vo.SatisfactionVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

/**
 * PackageName : smartbeans.portal.datahub.search.mapper
 * FileName : SearchMapper
 * Date : 2023-05-23
 * ===================================
 * Date  | Author   | note
 * ===================================
 * 2023-05-23    네이버시스템   최소 생성
 */
@Mapper("SatisfactionMapper")
public interface SatisfactionMapper {

    void insertSatisfaction(SatisfactionVO SatisfactionVo) throws Exception;

}
