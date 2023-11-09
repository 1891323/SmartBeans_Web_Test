package smartbeans.portal.admin.reference.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import smartbeans.portal.admin.reference.service.ReferenceAdminService;


@Controller
public class ReferenceAdminController {

    @Autowired
    private ReferenceAdminService referenceAdminService;
}
