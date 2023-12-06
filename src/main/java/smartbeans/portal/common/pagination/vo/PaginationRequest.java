package smartbeans.portal.common.pagination.vo;

import lombok.Data;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * file : PaginationVO.jsp
 * author kiboomhan
 * since 2023-05-30
 * version 1.0
 */
@Data
public class PaginationRequest {

    private PaginationInfo paginationInfo;
    private String jsFunction;
}
