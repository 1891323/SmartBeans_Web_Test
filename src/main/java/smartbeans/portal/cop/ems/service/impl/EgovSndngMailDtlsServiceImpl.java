package smartbeans.portal.cop.ems.service.impl;

import java.util.List;


import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smartbeans.cmmn.ComDefaultVO;
import smartbeans.portal.cop.ems.mapper.SndngMailDtlsMapper;
import smartbeans.portal.cop.ems.service.EgovSndngMailDetailService;
import smartbeans.portal.cop.ems.service.EgovSndngMailDtlsService;
import smartbeans.portal.cop.ems.vo.SndngMailVO;
import smartbeans.portal.utl.fcc.service.EgovStringUtil;

/**
 * 발송메일 내역을 조회하는 비즈니스 구현 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.12
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.12  박지욱          최초 생성
 *
 *  </pre>
 */
@Service("sndngMailDtlsService")
public class EgovSndngMailDtlsServiceImpl extends EgovAbstractServiceImpl implements EgovSndngMailDtlsService {

	@Autowired
	private SndngMailDtlsMapper sndngMailDtlsDAO;

	@Resource(name = "sndngMailDetailService")
	private EgovSndngMailDetailService sndngMailDetailService;

	/**
	 * 발송메일 목록을 조회한다.
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	@Override
	public List<?> selectSndngMailList(ComDefaultVO vo) throws Exception {
		return sndngMailDtlsDAO.selectSndngMailList(vo);
	}

	/**
	 * 발송메일 총건수를 조회한다.
	 * @param vo ComDefaultVO
	 * @return int
	 * @exception
	 */
	@Override
	public int selectSndngMailListTotCnt(ComDefaultVO vo) throws Exception {
		return sndngMailDtlsDAO.selectSndngMailListTotCnt(vo);
	}

	/**
	 * 발송메일을 삭제한다.
	 * @param vo SndngMailVO
	 * @exception
	 */
	@Override
	public void deleteSndngMailList(SndngMailVO vo) throws Exception {

		// 1. 발송메일을 삭제한다.
		String[] sbuf = EgovStringUtil.split(vo.getMssageId(), ",");
		for (int i = 0; i < sbuf.length; i++) {
			SndngMailVO sndngMailVO = new SndngMailVO();
			sndngMailVO.setMssageId(sbuf[i]);
			sndngMailDetailService.deleteSndngMail(sndngMailVO);
		}

		// 2. 첨부파일을 삭제한다.
		if (vo.getAtchFileIdList() != null) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
			String[] fbuf = EgovStringUtil.split(vo.getAtchFileIdList(), ",");
			for (int i = 0; i < fbuf.length; i++) {
				SndngMailVO sndngMailVO = new SndngMailVO();
				sndngMailVO.setAtchFileId(fbuf[i]);
				sndngMailDetailService.deleteAtchmnFile(sndngMailVO);
			}
		}
	}
}
