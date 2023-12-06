package smartbeans.portal.user.notice.service;
/*
 * 페이지네이션, 검색에 관한 내용 저장
 * 일반 게시판 정보는 Board 참고
 * */
import lombok.Data;
import org.egovframe.rte.fdl.cryptography.EgovEnvCryptoService;
import org.springframework.format.annotation.DateTimeFormat;
import smartbeans.cmmn.ComDefaultVO;

import java.util.Date;
@Data
public class UserNoticeVO extends ComDefaultVO {
    //게시글 순서
    private Long rowNum;

    // 게시글 번호
    private int noticeBoardNo;

    // 게시판 타입 (숫자형)
    private int noticeBoardType;

    // 게시판 하위 타입 (숫자형) 4: 공지사항
    private int noticeBoardSubType;

    // 마지막 업데이트 일시
    private Date noticeLastUpdtDtm;

    // 최초 등록 일시
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date noticeFirstRegistDtm;

    // 게시판 조회 수
    private int noticeViewCount;

    // 게시글 내용
    private String noticeContents;

    // 게시글 작성자
    private String noticeWrtr;

    // 비공개 게시글 여부
    private String noticePrvtPstYn;

    // 게시글 비밀번호
    private String noticePassword;

    // 답변 여부
    private String noticeAnsYn;

    // 삭제 여부
    private String noticeDelYn;

    // 회원 ID
    private String mbrId;

    // 게시글 카테고리
    private String noticeCat;

    // 게시글 제목
    private String noticeTitle;

    // 첨부파일 번호
    private String atchFileId;

    // 공지사항 상단 고정 여부
    private String noticeTopFixed;

    //QNA 질문글번호(계층구조)
    private int parentId;

    public Long getRowNum() {
        return rowNum;
    }

    public void setRowNum(Long rowNum) {
        this.rowNum = rowNum;
    }

    public int getNoticeBoardNo() {
        return noticeBoardNo;
    }

    public void setNoticeBoardNo(int noticeBoardNo) {
        this.noticeBoardNo = noticeBoardNo;
    }

    public int getNoticeBoardSubType() {
        return noticeBoardSubType;
    }

    public void setNoticeBoardSubType(int noticeBoardSubType) {
        this.noticeBoardSubType = noticeBoardSubType;
    }

    public Date getNoticeLastUpdtDtm() {
        return noticeLastUpdtDtm;
    }

    public void setNoticeLastUpdtDtm(Date noticeLastUpdtDtm) {
        this.noticeLastUpdtDtm = noticeLastUpdtDtm;
    }

    public Date getNoticeFirstRegistDtm() {
        return noticeFirstRegistDtm;
    }

    public void setNoticeFirstRegistDtm(Date noticeFirstRegistDtm) {
        this.noticeFirstRegistDtm = noticeFirstRegistDtm;
    }

    public int getNoticeViewCount() {
        return noticeViewCount;
    }

    public void setNoticeViewCount(int noticeViewCount) {
        this.noticeViewCount = noticeViewCount;
    }

    public String getNoticeContents() {
        return noticeContents;
    }

    public void setNoticeContents(String noticeContents) {
        this.noticeContents = noticeContents;
    }

    public String getNoticeWrtr() {
        return noticeWrtr;
    }

    public void setNoticeWrtr(String noticeWrtr) {
        this.noticeWrtr = noticeWrtr;
    }

    public String getNoticePrvtPstYn() {
        return noticePrvtPstYn;
    }

    public void setNoticePrvtPstYn(String noticePrvtPstYn) {
        this.noticePrvtPstYn = noticePrvtPstYn;
    }

    public String getNoticePassword() {
        return noticePassword;
    }

    public void setNoticePassword(String noticePassword) {
        this.noticePassword = noticePassword;
    }

    public String getNoticeAnsYn() {
        return noticeAnsYn;
    }

    public void setNoticeAnsYn(String noticeAnsYn) {
        this.noticeAnsYn = noticeAnsYn;
    }

    public String getNoticeDelYn() {
        return noticeDelYn;
    }

    public void setNoticeDelYn(String noticeDelYn) {
        this.noticeDelYn = noticeDelYn;
    }

    public String getMbrId() {
        return mbrId;
    }

    public void setMbrId(String mbrId) {
        this.mbrId = mbrId;
    }

    public String getNoticeCat() {
        return noticeCat;
    }

    public void setNoticeCat(String noticeCat) {
        this.noticeCat = noticeCat;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getAtchFileId() {
        return atchFileId;
    }

    public void setAtchFileId(String atchFileId) {  this.atchFileId = atchFileId;    }


    /**
     * 첨부파일ID 복호화 적용
     */
    public void setDecryptAtchFileId(EgovEnvCryptoService cryptoService) {
        if (this.atchFileId != null && !"".equals(this.atchFileId)) {
            this.atchFileId = cryptoService.decrypt(this.atchFileId);
        }
    }

}
