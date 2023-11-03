package smartbeans.portal.member.login.service;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MemberVO implements Serializable {

    private Long mbrId;                 // 회원 번호 시퀀스(SEQ_MEMBER_NO)
    private String userId;              // 회원 아이디
    private String password;            // 회원 비밀번호
    private String userNm;              // 회원 이름
    private String email;               // 회원 이메일
    private String telno;               // 회원 전화번호
    private Date birthday;              // 회원 생년월일
    private String addr;                // 회원 주소
    private Date mbrFrstRegistDtm;      // 회원 가입일
    private String mbrStatus;           // Y-활성, N-비활성
    private Date mbrLastUpdtDtm;        // 최종 수정 일자 및 시간
    private String userType;            // Y:관리자, N: 일반회원

}
