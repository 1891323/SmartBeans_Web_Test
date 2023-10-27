package smartbeans.portal.main.menu.mapper;

import org.apache.ibatis.annotations.Mapper;
import smartbeans.portal.main.menu.service.MenuVO;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<MenuVO> selectMenuList();

}
