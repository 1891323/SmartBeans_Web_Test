package smartbeans.portal.sym.mpm.vo;

import smartbeans.portal.sample.vo.SampleDefaultVO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrgrmAuthorVO extends SampleDefaultVO {

	private static final long serialVersionUID = 1L;

	/** 프로그램일련번호 */
	private Long prgrmSn;

	/** 프로그램ID */
	private String prgrmId;

	/** 프로그램URL */
	private String prgrmUrl;

	/** 프로그램명 */
	private String prgrmNm;

	/** 프로그램레벨 */
	private Integer prgrmLevel;

	/** 프로그램순서 */
	private Integer prgrmOrdr;

	/** 상위프로그램ID */
	private String upprgrmId;

	/** 배포여부 */
	private String rlsYn;

	/** 등록자ID */
	private String registerId;

	/** 등록일자 */
	private LocalDate regYmd;

	/** 수정자ID */
	private String updusrId;

	/** 수정일자 */
	private LocalDate updtYmd;
}
