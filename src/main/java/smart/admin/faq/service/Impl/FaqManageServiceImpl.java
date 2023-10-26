package smart.admin.faq.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.admin.faq.service.FaqManageService;

@Service
public class FaqManageServiceImpl implements FaqManageService {

    @Autowired
    private FaqManageDAO faqManageDAO;

}
