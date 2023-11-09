package smartbeans.portal.admin.faq.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smartbeans.portal.admin.faq.mapper.FaqAdminMapper;
import smartbeans.portal.admin.faq.service.FaqAdminService;


@Service
public class FaqAdminServiceImpl implements FaqAdminService {

    @Autowired
    private FaqAdminMapper faqAdminMapper;

}
