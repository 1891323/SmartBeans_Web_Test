package smartbeans.portal.admin.announcement.service;

import lombok.Data;
import smartbeans.cmmn.ComDefaultVO;

import java.util.Date;
@Data
public class NoticeBoardVO extends ComDefaultVO {

    // 게시글 번호
    private int noticeBoardNo;

    // 게시판 타입 (숫자형)
    private int noticeBoardType;

    // 게시판 하위 타입 (숫자형) 4: 공지사항
    private int noticeBoardSubType;

    // 마지막 업데이트 일시
    private Date noticeLastUpdtDtm;

    // 최초 등록 일시
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

    // 기본 생성자, getter, setter, toString() 메서드 등 추가적인 메서드는 생략...

}

