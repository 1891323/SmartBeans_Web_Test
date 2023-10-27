package smartbeans.portal.sm.service.impl;


import javax.annotation.Resource;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import smartbeans.cmmn.ComDefaultVO;
import smartbeans.portal.sm.mapper.SurvayMapper;
import smartbeans.portal.sm.service.SurvayService;

import java.util.List;


@Service("SurvayService")
public class SurvayServiceImpl extends EgovAbstractServiceImpl implements SurvayService {
    
	@Resource(name = "SurvayMapper")
	private SurvayMapper SurvaymMapper;


    /*@Override
	public void updateServaymenu(SurvaymVO vo) throws Exception {
		SurvayMapper.updateServaymenu(vo);
	}*/

	@Override
	public List<?> SelectSmList(ComDefaultVO searchVO) throws Exception {
		return SurvaymMapper.SelectSmList(searchVO);
	}
	
	@Override
	public int SelectListCnt(ComDefaultVO searchVO) {
		return SurvaymMapper.SelectListCnt(searchVO);
	}

}


