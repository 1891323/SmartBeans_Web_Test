package smartbeans.portal.main.menu.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import smartbeans.portal.main.menu.service.MenuService;
import smartbeans.portal.main.menu.service.MenuVO;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class MenuController {

    @Resource(name="MenuService")
    private MenuService menuService;

    @RequestMapping(value = "/layouts/topMenu.do")
    public String topMenu(ModelMap model) {
        List<MenuVO> menuList = menuService.selectMenuList();
        model.addAttribute("topMenu", menuList);
        return "layouts/common/TopMenu"; // view 반환
    }
}
