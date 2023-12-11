package smartbeans.portal.user.facility.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import smartbeans.portal.user.notice.web.UserNoticeController;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping(value= "/user/facility")
public class FacilityController {

    private static final Logger logger = LoggerFactory.getLogger(UserNoticeController.class);

    @RequestMapping(value="/ComprehensivePlan.do")
    public String comprehensiveView(){

        return "user/facility/ComprehensivePlan.lnb";
    }

    @RequestMapping(value="/MachineSupport.do")
    public String machinesupportView(){

        return "user/facility/MachineSupport.lnb";
    }

    @RequestMapping(value="/FarmingInfrastructure.do")
    public String farminfratView(){

        return "user/facility/FarmingInfrastructure.lnb";
    }

    @RequestMapping(value="/SmartSPC.do")
    public String smartspcView(){

        return "user/facility/SmartSPC.lnb";
    }

}
