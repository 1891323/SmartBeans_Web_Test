package chungnam.portal.sm.vo;

import lombok.Data;

@Data
public class SurvayVO {
    
//일련번호 
private Long sn;

//코멘트
private String commet;

//생성자 ID
private String create_id;

//메뉴 ID
private String menu_id;

//만족도
private String stft;

//등록일시
private String reg_data;

}
