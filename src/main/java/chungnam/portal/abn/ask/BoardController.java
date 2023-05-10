package chungnam.portal.abn.ask;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
    
    @RequestMapping("/UMA")
    public String UMA(ModelMap model){
        return "main/contents/admin/User/Menu/Add";
    }
    @RequestMapping("/UML")
    public String UML(ModelMap model){
        return "main/contents/admin/User/Menu/List";
    }
    @RequestMapping("/SM")
    public String SM(ModelMap model){
        return "main/contents/admin/Survay/Menu";
    }
    @RequestMapping("/SU")
    public String SU(ModelMap model){
        return "main/contents/admin/Survay/User";
    }
    @RequestMapping("/DM")
    public String DM(ModelMap model){
        return "main/contents/admin/DownData/Menu";
    }
    @RequestMapping("/BAA")
    public String BAA(ModelMap model){
        return "main/contents/admin/Board/Ask/Add";
    }
    @RequestMapping("/BAL")
    public String BAL(ModelMap model){
        return "main/contents/admin/Board/Ask/List";
    }
    @RequestMapping("/BNA")
    public String BNA(ModelMap model){
        return "main/contents/admin/Board/Notice/Add";
    }
    @RequestMapping("/BND")
    public String BND(ModelMap model){
        return "main/contents/admin/Board/Notice/Detail";
    }
    @RequestMapping("/BNL")
    public String BNL(ModelMap model){
        return "main/contents/admin/Board/Notice/List";
    }
    @RequestMapping("/AM")
    public String AM(ModelMap model){
        return "main/contents/admin/Access/Menu";
    }
    @RequestMapping("/AU")
    public String AU(ModelMap model){
        return "main/contents/admin/Access/User";
    }
}
