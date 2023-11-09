
package smartbeans.portal.common.satsfc.service.impl;

import smartbeans.portal.common.satsfc.mapper.SatisfactionMapper;
import smartbeans.portal.common.satsfc.service.SatisfactionService;
import smartbeans.portal.common.satsfc.vo.SatisfactionVO;
import lombok.RequiredArgsConstructor;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * PackageName : smartbeans.portal.datahub.search.service.impi
 * FileName : DataSearchServiceimpi
 * Date : 2023-05-23
 * ===================================
 * Date  | Author   | note
 * ===================================
 * 2023-05-23    네이버시스템   최소 생성
 */
@Service("SatisfactionService")
@RequiredArgsConstructor
public class SatisfactionServiceimpi extends EgovAbstractServiceImpl implements SatisfactionService {


    @Resource(name = "SatisfactionMapper")
    private SatisfactionMapper satisfactionMapper;



    public void insertSatisfaction(SatisfactionVO SatisfactionVO) throws Exception {
        satisfactionMapper.insertSatisfaction(SatisfactionVO);
    }


}
