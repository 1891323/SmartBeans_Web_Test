package smartbeans.portal.user.farminfo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/farminfo")
public class FarmInfoController {
    @RequestMapping("/WeatherInformation.do")
    public String WeatherInformation() {
        return "/user/farminfo/WeatherInformation.index";
    }
}
