package smartbeans.portal.main.menu.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MenuVO {
    public int menu_id;

    public String menu_url;

    public String menu_name;

    public String menu_parent_id;

    public String menu_admin_parent_id;
