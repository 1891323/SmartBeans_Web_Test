package smartbeans.portal.main.menu.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MenuVO {
    public int menu_no;

    public String menu_id;

    public String menu_name;

    public String menu_parent_id;
}
