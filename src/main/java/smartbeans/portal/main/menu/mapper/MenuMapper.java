package smartbeans.portal.main.menu.mapper;

import org.apache.ibatis.annotations.Mapper;
import smartbeans.portal.main.menu.service.MenuVO;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenuMapper {

    List<MenuVO> selectMenuList(Map<String, Object> param);

    List<MenuVO> selectMenuListWithAdmin();
}
