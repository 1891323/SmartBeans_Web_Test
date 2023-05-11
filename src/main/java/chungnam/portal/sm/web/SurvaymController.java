package chungnam.portal.sm.web;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import chungnam.cmmn.ComDefaultVO;
import chungnam.portal.sm.service.SurvayService;

@Controller
public class SurvaymController {

    @Resource(name = "SurvayService")
    private SurvayService SurvayService;

    /**
     * EgovPropertyService
     */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;


    @RequestMapping(value= "/ServayMenu.do")
    public String SelectServayMenuList(@ModelAttribute("searchVO") ComDefaultVO searchVO, ModelMap model)throws Exception{
        
         /** EgovPropertyService.prgrm */
         searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
         searchVO.setPageSize(propertiesService.getInt("pageSize"));
 
         /** pageing setting */
         PaginationInfo paginationInfo = new PaginationInfo();
         paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
         paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
         paginationInfo.setPageSize(searchVO.getPageSize());
        
         searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
         searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
         searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

         List<?> ServayM = SurvayService.SelectSmList(searchVO);
         model.addAttribute("resultList", ServayM);

         int totCnt = SurvayService.SelectListCnt(searchVO);
         paginationInfo.setTotalRecordCount(totCnt);
         model.addAttribute("paginationInfo", paginationInfo);

        return "main/content";
    }
    
}
