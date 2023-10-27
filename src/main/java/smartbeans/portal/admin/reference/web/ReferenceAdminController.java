package smart.beans.admin.reference.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import smart.beans.admin.reference.service.ReferenceAdminService;

@Controller
public class ReferenceAdminController {

    @Autowired
    private ReferenceAdminService referenceAdminService;
}
