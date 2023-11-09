package smartbeans.cmmn;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 게시물에 대한 데이터 처리 모델 클래스
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009.03.06
 * @version 1.0
 * @see
 *  
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.06  이삼섭          최초 생성
 *  2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성 
 *  
 *  </pre>
 */

@Data
public class Board implements Serializable {

	/**
	 * 게시물 첨부파일 아이디
	 */
	private String fileNo = "";
	/**
	 * 게시판 아이디
	 */
	private String bbsId = "";

	/**
	 * 최초등록시점
	 */
	private String frstRegisterPnttm = "";
	/**
	 * 최종수정시점
	 */
	private String lastUpdusrPnttm = "";
	/**
	 * 게시자 아이디
	 */
	private String ntcrId = "";
	/**
	 * 게시자명
	 */
	private String ntcrNm = "";
	/**
	 * 게시물 내용
	 */
	private String nttCn = "";
	/**
	 * 게시물 아이디
	 */
	private long nttId = 0L;
	/**
	 * 게시물 번호
	 */
	private long nttNo = 0L;
	/**
	 * 게시물 제목
	 */
	private String nttSj = "";
	/**
	 * 부모글번호
	 */
	private String parnts = "0";
	/**
	 * 패스워드
	 */
	private String password = "";
	/**
	 * 조회수
	 */
	private int inqireCo = 0;
	/**
	 * 답장여부
	 */
	private String replyAt = "";
	/**
	 * 답장위치
	 */
	private String replyLc = "0";

	/**
	 * 삭제여부
	 */
	private String useAt = "";
	

}
