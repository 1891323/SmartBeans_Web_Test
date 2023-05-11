/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package chungnam.portal.sym.mpm.web;

import chungnam.cmmn.ComDefaultVO;
import chungnam.portal.sym.mpm.service.PrgrmService;
import chungnam.portal.sym.mpm.vo.PrgrmVO;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class PrgrmController {

    @Resource(name = "PrgrmService")
    private PrgrmService prgrmService;

    /**
     * EgovPropertyService
     */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /**
     * Validator
     */
    @Resource(name = "beanValidator")
    protected DefaultBeanValidator beanValidator;

    @RequestMapping(value = "/prgrmList.do")
    public String selectPrgrmList(@ModelAttribute("searchVO") ComDefaultVO searchVO, ModelMap model) throws Exception {

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

        List<?> prgrmList = prgrmService.selectPrgrmListForAuth(searchVO);
        model.addAttribute("resultList", prgrmList);

        int totCnt = prgrmService.selectPrgrmListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return "sym/mnu/prgrmList";
    }

/*
    @RequestMapping(value = "/addPrgrm.do", method = RequestMethod.GET)
    public String addPrgrmView(@ModelAttribute("searchVO") ComDefaultVO searchVO, Model model) throws Exception {
        model.addAttribute("prgrmVO", new PrgrmVO());
        return "prgrm/prgrmRegister";
    }*/


    @RequestMapping(value = "/addPrgrm.do", method = RequestMethod.POST)
    public String addPrgrm(@ModelAttribute("searchVO") ComDefaultVO searchVO, PrgrmVO prgrmVO, BindingResult bindingResult, Model model, SessionStatus status)
            throws Exception {

        // Server-Side Validation
        beanValidator.validate(prgrmVO, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("prgrmVO", prgrmVO);
            return "sym/mnu/prgrmList";
        }

        prgrmService.insertPrgrm(prgrmVO);
        status.setComplete();
        return "forward:/prgrmList.do";
    }


/*
    @RequestMapping("/updatePrgrmView.do")
    public String updatePrgrmView(@RequestParam("selectedId") String id, @ModelAttribute("searchVO") ComDefaultVO searchVO, Model model) throws Exception {
        PrgrmVO prgrmVO = new PrgrmVO();
        prgrmVO.setId(id);
        // 변수명은 CoC 에 따라 prgrmVO
        model.addAttribute(selectPrgrm(prgrmVO, searchVO));
        return "prgrm/prgrmRegister";
    }
*/

    public PrgrmVO selectPrgrm(PrgrmVO prgrmVO, @ModelAttribute("searchVO") ComDefaultVO searchVO) throws Exception {
        return prgrmService.selectPrgrm(prgrmVO);
    }

    @RequestMapping("/updatePrgrm.do")
    public String updatePrgrm(@ModelAttribute("searchVO") ComDefaultVO searchVO, PrgrmVO prgrmVO, BindingResult bindingResult, Model model, SessionStatus status)
            throws Exception {

        beanValidator.validate(prgrmVO, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("prgrmVO", prgrmVO);
            return "prgrm/prgrmRegister";
        }

        prgrmService.updatePrgrm(prgrmVO);
        status.setComplete();
        return "forward:/prgrmList.do";
    }

    @RequestMapping("/deletePrgrm.do")
    public String deletePrgrm(PrgrmVO prgrmVO, @ModelAttribute("searchVO") ComDefaultVO searchVO, SessionStatus status) throws Exception {
        prgrmService.deletePrgrm(prgrmVO);
        status.setComplete();
        return "forward:/prgrmList.do";
    }


}
