package smartbeans.portal.notice.vo;

import lombok.Data;

@Data
public class NoticeVO {
	private int bbs_sn;					// 시퀀스번호
	private String ntt_se;				// 게시판 구분
	private String sj;					// 제목
	private String cn;					// 내용
	private String create_id;			// 등록자
	private String reg_dttm;			// 등록일시
	private String mdfcn;				// 수정자
	private String mdfcn_dttm;			// 수정일시
	private int upper_bbs_sn;			// 상위게시판번호
	private int rdcnt;					// 조회수
	private String answer_sttus;		// 답변상태
	private String user_id;				// 사용자 아이디
	private String answer_cn;			// 답변내용
	private String othbsc_yn;			// 공개 여부
	private String upend_fixing_yn;		// 상단고정여부
}