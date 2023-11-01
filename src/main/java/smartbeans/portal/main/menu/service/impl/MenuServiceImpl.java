package smartbeans.portal.main.menu.service.impl;

import org.springframework.stereotype.Service;
import smartbeans.portal.main.menu.mapper.MenuMapper;
import smartbeans.portal.main.menu.service.MenuService;
import smartbeans.portal.main.menu.service.MenuVO;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("MenuService")
public class MenuServiceImpl implements MenuService {
    @Resource(name="menuMapper")
    private MenuMapper menuMapper;
    @Override
    public List<MenuVO> selectMenuList() {
        int value = 6;
        Map<String, Object> param = new HashMap<>();
        param.put("no", value);
        return menuMapper.selectMenuList(param);
    }

    @Override
    public List<MenuVO> selectMenuListWithAdmin() {
        return menuMapper.selectMenuListWithAdmin();
    }
}
