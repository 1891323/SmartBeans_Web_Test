package chungnam.portal.Main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class mainController {
    
    @RequestMapping("/index")
    public String index(ModelMap model){
        return "main/main_index";
    }

    @RequestMapping("/header")
    public String header(ModelMap model){
        return "main/header";
    }

    @RequestMapping("/footer")
    public String footer(ModelMap model){
        return "main/footer";
    }
}
