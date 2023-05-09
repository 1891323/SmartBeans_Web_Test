package chungnam.portal.cop.ems.mapper;

import java.util.List;

import chungnam.cmmn.ComDefaultVO;

public interface SndngMailDtlsMapper {
	/**
	 * 발송메일 목록을 조회한다.
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectSndngMailList(ComDefaultVO vo) throws Exception;

	/**
	 * 발송메일 총건수를 조회한다.
	 * @param vo ComDefaultVO
	 * @return int
	 * @exception
	 */
	public int selectSndngMailListTotCnt(ComDefaultVO vo);
}
