package smartbeans.portal.main.menu.service.impl;

import org.springframework.stereotype.Service;
import smartbeans.portal.main.menu.mapper.MenuMapper;
import smartbeans.portal.main.menu.service.MenuService;
import smartbeans.portal.main.menu.service.MenuVO;

import javax.annotation.Resource;
import java.util.List;


@Service("MenuService")
public class MenuServiceImpl implements MenuService {
    @Resource(name="menuMapper")
    private MenuMapper menuMapper;
    @Override
    public List<MenuVO> selectMenuList() throws Exception {
        return menuMapper.selectMenuList();
    }
}
