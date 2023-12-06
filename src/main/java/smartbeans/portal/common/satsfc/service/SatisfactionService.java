package smartbeans.portal.common.satsfc.service;

import smartbeans.portal.common.satsfc.vo.SatisfactionVO;

/**
 * PackageName : smartbeans.portal.datahub.search.service
 * FileName : DataSearchService
 * Date : 2023-05-23
 * ===================================
 * Date  | Author   | note
 * ===================================
 * 2023-05-23    네이버시스템   최소 생성
 */
public interface SatisfactionService {

    void insertSatisfaction(SatisfactionVO SatisfactionVo) throws Exception;

}
