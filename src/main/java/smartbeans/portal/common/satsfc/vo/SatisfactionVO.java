package smartbeans.portal.common.satsfc.vo;

import smartbeans.cmmn.ComDefaultVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * PackageName : smartbeans.portal.datahub.search.vo
 * FileName : SerachVO
 * Date : 2023-05-23
 * ===================================
 * Date  | Author   | note
 * ===================================
 * 2023-05-23    네이버시스템   최소 생성
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SatisfactionVO extends ComDefaultVO {

    private long dgstfnSn;
    private String dgstfnCn;
    private String dgstfnScr;
    private String prgrmId;
    private String registerId;
    private String regDt;

}
