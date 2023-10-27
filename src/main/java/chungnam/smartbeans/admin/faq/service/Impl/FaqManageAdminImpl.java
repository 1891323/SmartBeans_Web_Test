package smart.beans.admin.faq.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.beans.admin.faq.mapper.FaqAdminMapper;
import smart.beans.admin.faq.service.FaqAdminService;

@Service
public class FaqManageAdminImpl implements FaqAdminService {

    @Autowired
    private FaqAdminMapper faqManageDAO;

}
