package chungnam.portal.sm.service.impl;


import javax.annotation.Resource;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import chungnam.cmmn.ComDefaultVO;
import chungnam.portal.sm.mapper.SurvayMapper;
import chungnam.portal.sm.service.SurvayService;
import chungnam.portal.sm.vo.SurvayVO;

import javax.annotation.Resource;
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


