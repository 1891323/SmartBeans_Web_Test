package smartbeans.portal.user.farminfo.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WeatherVO {
    private String weather;
    private String tmp;
    private String pop;
    private String reh;
    private String wsd;
    private String today;
    private String img;
    public WeatherVO(String tmp, String pop, String today, String img) {
        this.tmp = tmp;
        this.pop = pop;
        this.today = today;
        this.img = img;
    }
}
