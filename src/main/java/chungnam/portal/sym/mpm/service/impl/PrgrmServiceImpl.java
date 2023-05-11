package chungnam.portal.sym.mpm.service.impl;

import chungnam.cmmn.ComDefaultVO;
import chungnam.portal.sym.mpm.mapper.PrgrmMapper;
import chungnam.portal.sym.mpm.service.PrgrmService;
import chungnam.portal.sym.mpm.vo.PrgrmVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("PrgrmService")
public class PrgrmServiceImpl extends EgovAbstractServiceImpl implements PrgrmService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PrgrmServiceImpl.class);

	@Resource(name = "prgrmMapper")
	private PrgrmMapper prgrmMapper;

	@Override
	public PrgrmVO insertPrgrm(PrgrmVO vo) throws Exception {
		LOGGER.debug(vo.toString());

		prgrmMapper.insertPrgrm(vo);
		return vo;
	}

	@Override
	public void updatePrgrm(PrgrmVO vo) throws Exception {
		prgrmMapper.updatePrgrm(vo);
	}

	@Override
	public void deletePrgrm(PrgrmVO vo) throws Exception {
		prgrmMapper.deletePrgrm(vo);
	}

	@Override
	public PrgrmVO selectPrgrm(PrgrmVO vo) throws Exception {
		PrgrmVO resultVO = prgrmMapper.selectPrgrm(vo);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}

	@Override
	public List<?> selectPrgrmList(ComDefaultVO searchVO) throws Exception {
		return prgrmMapper.selectPrgrmList(searchVO);
	}

	@Override
	public List<?> selectPrgrmListForAuth(ComDefaultVO searchVO) throws Exception {
		return prgrmMapper.selectPrgrmListForAuth(searchVO);
	}

	@Override
	public int selectPrgrmListTotCnt(ComDefaultVO searchVO) {
		return prgrmMapper.selectPrgrmListTotCnt(searchVO);
	}

}
