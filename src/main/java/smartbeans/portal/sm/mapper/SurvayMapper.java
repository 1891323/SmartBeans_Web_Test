package smartbeans.portal.sm.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import smartbeans.cmmn.ComDefaultVO;


@Mapper("SurvayMapper")
public interface SurvayMapper {
    
    //SurvaymVO insertServayM(SurvaymVO vo) throws Exception;

	//void updateServaymenu(SurvaymVO vo) throws Exception;

	List<?> SelectSmList(ComDefaultVO searchVO) throws Exception; // 메뉴관리 리스트

	int SelectListCnt(ComDefaultVO searchVO); //메뉴관리 페이징
}
