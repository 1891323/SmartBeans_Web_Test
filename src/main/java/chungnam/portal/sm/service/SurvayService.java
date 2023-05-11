package chungnam.portal.sm.service;

import java.util.List;

import chungnam.cmmn.ComDefaultVO;
import chungnam.portal.sm.vo.SurvayVO;
import org.springframework.stereotype.Service;

public interface SurvayService {
    
    //SurvaymVO insertServayM(SurvaymVO vo) throws Exception;

	//void updateServaymenu(SurvaymVO vo) throws Exception;

	List<?> SelectSmList(ComDefaultVO searchVO) throws Exception; // 메뉴관리 리스트

	int SelectListCnt(ComDefaultVO searchVO); //메뉴관리 페이징
}
