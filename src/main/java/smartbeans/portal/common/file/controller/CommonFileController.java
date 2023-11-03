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
package smartbeans.portal.common.file.controller;

import smartbeans.cmmn.service.FileVO;
import lombok.RequiredArgsConstructor;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * file : CommonFileController.java
 * author kiboomhan
 * since 2023-05-24
 * version 1.0
 */
@Controller
@RequiredArgsConstructor
public class CommonFileController {

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

    @RequestMapping(value = "/common/fileView.do")
    public String fileView(@ModelAttribute("searchVO") FileVO fileVO,
                                       HttpServletRequest request,
                                       @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {

        String param_atchFileId = (String) commandMap.get("param_atchFileId");
        String param_mode = (String) commandMap.get("param_mode");
        String param_key = (String) commandMap.get("param_key");

        model.addAttribute("param_atchFileId", param_atchFileId);
        model.addAttribute("param_mode", param_mode);
        model.addAttribute("param_key", param_key);

        return "common/file/fileView";
    }
    @RequestMapping(value = "/common/fileEdit.do")
    public String fileEdit(@ModelAttribute("searchVO") FileVO fileVO,
                                       HttpServletRequest request,
                                       @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {

        String param_atchFileId = (String) commandMap.get("param_atchFileId");
        String param_mode = (String) commandMap.get("param_mode");

        model.addAttribute("param_atchFileId", param_atchFileId);
        model.addAttribute("param_mode", param_mode);

        return "common/file/fileEdit";
    }
}
