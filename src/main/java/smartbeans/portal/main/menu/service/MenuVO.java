package smartbeans.portal.main.menu.service;

import lombok.Data;

@Data
public class MenuVO {
    public int menu_no;

    public String menu_id;

    public String menu_name;

    public int menu_parent_id;
}
