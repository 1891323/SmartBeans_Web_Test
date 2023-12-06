package smartbeans.portal.user.farminfo.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PriceStatusVO {
    private String productName;
    private String unit;
    private String dpr1;
    private String dpr2;
    private String dpr3;
    private String dpr4;

    public PriceStatusVO() {

    }
}
