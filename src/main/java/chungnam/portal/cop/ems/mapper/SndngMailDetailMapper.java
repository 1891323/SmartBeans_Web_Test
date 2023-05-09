package chungnam.portal.cop.ems.mapper;

import chungnam.portal.cop.ems.vo.SndngMailVO;

public interface SndngMailDetailMapper {
	/**
	 * 발송메일을 상세 조회한다.
	 * @param vo SndngMailVO
	 * @return SndngMailVO
	 * @exception Exception
	 */
	public SndngMailVO selectSndngMail(SndngMailVO vo) throws Exception;

	/**
	 * 발송메일을 삭제한다.
	 * @param vo SndngMailVO
	 * @exception
	 */
	public void deleteSndngMail(SndngMailVO vo) throws Exception;
}
