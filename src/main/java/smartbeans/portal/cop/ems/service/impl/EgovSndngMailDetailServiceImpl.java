package smartbeans.portal.cop.ems.service.impl;

import java.io.File;


import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smartbeans.cmmn.service.EgovFileMngService;
import smartbeans.cmmn.service.FileVO;
import smartbeans.cmmn.service.Globals;
import smartbeans.portal.cop.ems.mapper.SndngMailDetailMapper;
import smartbeans.portal.cop.ems.service.EgovSndngMailDetailService;
import smartbeans.portal.cop.ems.vo.SndngMailVO;
import smartbeans.portal.utl.sim.service.EgovFileTool;

/**
 * 발송메일을 상세 조회하는 비즈니스 구현 클래스
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
@Service("sndngMailDetailService")
public class EgovSndngMailDetailServiceImpl extends EgovAbstractServiceImpl implements EgovSndngMailDetailService {

	// 파일구분자
	static final char FILE_SEPARATOR = File.separatorChar;

	@Autowired
	private SndngMailDetailMapper sndngMailDetailDAO;

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService egovFileMngService;

	/**
	 * 발송메일을 상세 조회한다.
	 * @param vo SndngMailVO
	 * @return SndngMailVO
	 * @exception Exception
	 */
	@Override
	public SndngMailVO selectSndngMail(SndngMailVO vo) throws Exception {

		// 1. 발송메일 정보를 조회한다.
		SndngMailVO resultMailVO = sndngMailDetailDAO.selectSndngMail(vo);

		return resultMailVO;
	}

	/**
	 * 발송메일을 삭제한다.
	 * @param vo SndngMailVO
	 * @exception
	 */
	@Override
	public void deleteSndngMail(SndngMailVO vo) throws Exception {

		// 1. 발송메일을 삭제한다.
		sndngMailDetailDAO.deleteSndngMail(vo);

		// 2. 발송요청XML파일을 삭제한다.
		String xmlFile = Globals.MAIL_REQUEST_PATH + vo.getMssageId() + ".xml";
		EgovFileTool.deleteFile(xmlFile);
	}

	/**
	 * 첨부파일을 삭제한다.
	 * @param vo SndngMailVO
	 * @exception
	 */
	@Override
	public void deleteAtchmnFile(SndngMailVO vo) throws Exception {

		// 1. 첨부파일 목록을 삭제한다. (이삼섭 책임 제공)
		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(vo.getAtchFileId());
		egovFileMngService.deleteAllFileInf(fileVO);
	}
}
