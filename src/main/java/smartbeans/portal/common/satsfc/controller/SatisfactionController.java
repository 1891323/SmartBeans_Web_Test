package smartbeans.portal.common.satsfc.controller;

import smartbeans.portal.common.satsfc.service.SatisfactionService;
import smartbeans.portal.common.satsfc.vo.SatisfactionVO;
import lombok.RequiredArgsConstructor;
import org.egovframe.rte.fdl.cmmn.exception.EgovBizException;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * PackageName : smartbeans.portal.datahub.search.controller
 * FileName : Satisfaction
 * Date : 2023-05-24
 * ===================================
 * Date  | Author   | note
 * ===================================
 * 2023-05-24    네이버시스템   최소 생성
 */
@Controller
@RequestMapping(value = "/common")
@RequiredArgsConstructor
public class SatisfactionController {
    @Resource(name = "SatisfactionService")
    protected SatisfactionService satisfactionService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;


    @RequestMapping(value = "/goSatisfaction.do")
    public String goSatisfaction() throws Exception {
        return "common/SatisFacion";
    }

    @PostMapping(value = "/insertSatisfaction.do")
    @ResponseBody
    public Map<String, String> insertSatisfaction(@RequestBody SatisfactionVO satisfactionVO, SessionStatus status) throws Exception {
        Map<String, String> data = new HashMap<>();
        data.put("status", "error");

        try {
            satisfactionService.insertSatisfaction(satisfactionVO);
            data.put("status", "success");
        } catch (EgovBizException e) {
            data.put("alertMessage", e.getMessage());
        }
        return data;
    }

}
