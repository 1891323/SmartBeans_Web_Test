package smartbeans.boot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import smartbeans.cmmn.web.EgovBindingInitializer;
import smartbeans.cmmn.web.EgovImgPaginationRenderer;
import smartbeans.config.interceptor.AccessInterceptor;
import smartbeans.portal.member.login.service.MemberLoginService;
import smartbeans.portal.sec.security.filter.EgovSpringSecurityLoginFilter;
import smartbeans.portal.sec.security.filter.EgovSpringSecurityLogoutFilter;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.DefaultPaginationManager;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationRenderer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Configuration
public class DatahubWebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private MemberLoginService memberLoginService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/cmmn/validator.do").setViewName("cmmn/validator");
        registry.addViewController("/").setViewName("forward:/index.jsp");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        Properties prop = new Properties();
        prop.setProperty("org.springframework.dao.DataAccessException", "cmmn/dataAccessFailure");
        prop.setProperty("org.springframework.transaction.TransactionException", "cmmn/transactionFailure");
        prop.setProperty("org.egovframe.rte.fdl.cmmn.exception.EgovBizException", "cmmn/egovError");
        prop.setProperty("org.springframework.security.AccessDeniedException", "cmmn/egovError");
        prop.setProperty("java.lang.Throwable", "cmmn/egovError");

        Properties statusCode = new Properties();
        statusCode.setProperty("cmmn/egovError", "400");
        statusCode.setProperty("cmmn/egovError", "500");

        SimpleMappingExceptionResolver smer = new SimpleMappingExceptionResolver();
        smer.setDefaultErrorView("cmmn/egovError");
        smer.setExceptionMappings(prop);
        smer.setStatusCodes(statusCode);
        exceptionResolvers.add(smer);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(new AccessInterceptor());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/");
    }


    @Bean
    public TilesConfigurer tilesConfigurer() {
        final TilesConfigurer configurer = new TilesConfigurer();
        String[] definitions = {"classpath:tiles/tiles.xml"};
        configurer.setDefinitions(definitions);
        configurer.setCheckRefresh(true);
//		configurer.setCompleteAutoload(true);
        return configurer;
    }

    @Bean
    public UrlBasedViewResolver tilesViewResolver() {
        UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
        tilesViewResolver.setViewClass(TilesView.class);
        tilesViewResolver.setOrder(0);
        return tilesViewResolver;
    }

    @Bean
    public UrlBasedViewResolver urlBasedViewResolver() {
        UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
        urlBasedViewResolver.setOrder(1);
        urlBasedViewResolver.setViewClass(JstlView.class);
        urlBasedViewResolver.setPrefix("/WEB-INF/jsp/smartbeans/portal/");
        urlBasedViewResolver.setSuffix(".jsp");
        return urlBasedViewResolver;
    }

    @Bean
    public EgovImgPaginationRenderer imageRenderer() {
        return new EgovImgPaginationRenderer();
    }

    @Bean
    public DefaultPaginationManager paginationManager(EgovImgPaginationRenderer imageRenderer) {
        Map<String, PaginationRenderer> rendererType = new HashMap<>();
        rendererType.put("image", imageRenderer);
        DefaultPaginationManager defaultPaginationManager = new DefaultPaginationManager();
        defaultPaginationManager.setRendererType(rendererType);
        return defaultPaginationManager;
    }

    @Bean
    public SessionLocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("language");
        return interceptor;
    }

    @Bean
    public FilterRegistrationBean encodingFilterBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setForceEncoding(true);
        filter.setEncoding("UTF-8");
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("*.do");
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter rmha = super.createRequestMappingHandlerAdapter();
        rmha.setWebBindingInitializer(new EgovBindingInitializer());
        return rmha;
    }


    @Bean
    public FilterRegistrationBean securityLoginFilter(@Qualifier("smartbeansLoginService") MemberLoginService memberLoginService) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        EgovSpringSecurityLoginFilter filter = new EgovSpringSecurityLoginFilter(memberLoginService);
        registrationBean.addInitParameter("loginURL", "/member/login.do");
        registrationBean.addInitParameter("loginProcessURL", "/member/login/actionLogin.do");
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("*.do");
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean securityLogoutFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        EgovSpringSecurityLogoutFilter filter = new EgovSpringSecurityLogoutFilter();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/uat/uia/actionLogout.do");
        return registrationBean;
    }

    @Bean(name = "jsonView")
    public MappingJackson2JsonView jsonView() {
        return new MappingJackson2JsonView();
    }

}
