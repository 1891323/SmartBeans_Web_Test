package smartbeans.portal.main.menu.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import smartbeans.portal.main.menu.service.MenuService;
import smartbeans.portal.main.menu.service.MenuVO;

import javax.annotation.Resource;
import java.util.List;

import static com.squareup.okhttp.internal.Internal.logger;

@Controller
public class MenuController {

    @Resource(name="MenuService")
    private MenuService menuService;

    @RequestMapping(value = "/layouts/topMenu.do")

    public String selectHeader(ModelMap model) {
        // 나중에 로그인 구분 추가 예정
        //List<MenuVO> menuList = menuService.selectMenuList(); // 비로그인 시
        List<MenuVO> menuList = menuService.selectMenuListWithAdmin(); // 관리자 로그인 시
        model.addAttribute("topMenu", menuList);
        return "layouts/common/TopMenu";

    }
}
