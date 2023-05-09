package chungnam.portal.cop.ems.mapper;

import java.util.List;

import chungnam.portal.cop.ems.vo.SndngMailVO;

public interface SndngMailRegistMapper {
	/**
	 * 발송할 메일을 등록한다
	 * @param vo SndngMailVO
	 * @return SndngMailVO
	 * @exception Exception
	 */
	public SndngMailVO insertSndngMail(SndngMailVO vo) throws Exception;

	/**
	 * 발송할 메일에 있는 첨부파일 목록을 조회한다.
	 * @param vo SndngMailVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectAtchmnFileList(SndngMailVO vo) throws Exception;

	/**
	 * 발송결과를 수정한다.
	 * @param vo SndngMailVO
	 * @return SndngMailVO
	 * @exception Exception
	 */
	public SndngMailVO updateSndngMail(SndngMailVO vo) throws Exception;
}
