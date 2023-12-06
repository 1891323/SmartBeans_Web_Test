package smartbeans.portal.sm.service;

import java.util.List;

import smartbeans.cmmn.ComDefaultVO;

public interface SurvayService {
    
    //SurvaymVO insertServayM(SurvaymVO vo) throws Exception;

	//void updateServaymenu(SurvaymVO vo) throws Exception;

	List<?> SelectSmList(ComDefaultVO searchVO) throws Exception; // 메뉴관리 리스트

	int SelectListCnt(ComDefaultVO searchVO); //메뉴관리 페이징
}
