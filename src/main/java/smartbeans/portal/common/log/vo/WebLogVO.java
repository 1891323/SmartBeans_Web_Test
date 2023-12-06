package smartbeans.portal.common.log.vo;

import lombok.Builder;
import lombok.Data;

/**
 * file : CntnLog.jsp
 * author kiboomhan
 * since 2023-05-30
 * version 1.0
 */

@Data
@Builder
public class WebLogVO {

    /** 웹로그일련번호 */
    private Long webLogSn;

    /** url주소 */
    private String urlAddr;

    /** url이전주소 */
    private String urlBfrAddr;

    /** 요청자ID */
    private String rqstrId;

    /** 요청자IP */
    private String rqstrIpAddr;

    /** 프로그램ID */
    private String prgrmId;

    /** 등록자ID */
    private String regId;

    /** 등록일자 */
    private String regDt;

}
