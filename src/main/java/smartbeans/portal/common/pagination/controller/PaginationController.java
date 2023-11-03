package smartbeans.portal.common.pagination.controller;

import smartbeans.portal.common.pagination.vo.PaginationRequest;
import lombok.RequiredArgsConstructor;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.DefaultPaginationManager;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationRenderer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * file : PaginationController.jsp
 * author kiboomhan
 * since 2023-05-30
 * version 1.0
 */
@Controller
@RequiredArgsConstructor
public class PaginationController {

    final private DefaultPaginationManager paginationManager;

    @RequestMapping(value = "/common/pagination.do")
    @ResponseBody
    public Map<String, ?> pagination(@RequestBody PaginationRequest paginationRequest) throws Exception {
        Map<String, Object> result = new HashMap<>();
        PaginationRenderer image = paginationManager.getRendererType("image");
        String html = image.renderPagination(paginationRequest.getPaginationInfo(), paginationRequest.getJsFunction());
        result.put("result", html);
        return result;
    }
}
