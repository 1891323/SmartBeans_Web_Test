package smartbeans.portal.main.menu.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import smartbeans.portal.main.menu.service.MenuService;

import javax.annotation.Resource;

import static com.squareup.okhttp.internal.Internal.logger;

@Controller
public class MenuController {

    @Resource(name="MenuService")
    private MenuService menuService;

    @RequestMapping(value = "/layouts/topMenu.do")
    public String topMenu(ModelMap model) throws Exception {
        String[] menuNames = new String[]{"사업소개", "단지내 주요 시설물", "농업정보", "알림마당", "센터소개"};
        model.addAttribute("topMenu", menuNames);

        System.out.println("hihi "+menuService.selectMenuList());
        logger.info("메뉴테스트중=====================:"+ menuService.selectMenuList());
        return "layouts/common/TopMenu"; // view 반환
    }
}
