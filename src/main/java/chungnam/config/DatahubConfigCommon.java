package chungnam.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.trace.LeaveaTrace;
import org.egovframe.rte.fdl.cmmn.trace.handler.DefaultTraceHandler;
import org.egovframe.rte.fdl.cmmn.trace.handler.TraceHandler;
import org.egovframe.rte.fdl.cmmn.trace.manager.DefaultTraceHandleManager;
import org.egovframe.rte.fdl.cmmn.trace.manager.TraceHandlerService;
import org.egovframe.rte.fdl.cryptography.EgovEnvCryptoService;
import org.egovframe.rte.fdl.cryptography.EgovPasswordEncoder;
import org.egovframe.rte.fdl.cryptography.impl.EgovARIACryptoServiceImpl;
import org.egovframe.rte.fdl.cryptography.impl.EgovEnvCryptoServiceImpl;
import org.egovframe.rte.fdl.excel.EgovExcelService;
import org.egovframe.rte.fdl.excel.impl.EgovExcelServiceImpl;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.DefaultPaginationManager;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationRenderer;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import chungnam.cmmn.EgovComTraceHandler;
import chungnam.cmmn.EgovMessageSource;
import chungnam.cmmn.ImagePaginationRenderer;
import chungnam.cmmn.config.EgovLoginConfig;
import chungnam.cmmn.properties.EgovProperties;
import chungnam.cmmn.service.EgovUserDetailsService;
import chungnam.cmmn.util.EgovUserDetailsHelper;
import chungnam.cmmn.util.EgovWildcardReloadableResourceBundleMessageSource;
import chungnam.cmmn.web.EgovMultipartResolver;
import chungnam.portal.sec.ram.service.impl.EgovUserDetailsSecurityServiceImpl;

@Configuration
@ComponentScan(basePackages = "chungnam", includeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Service.class),
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Repository.class)
	}, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class),
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
	})
public class DatahubConfigCommon {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Bean
	public AntPathMatcher antPathMatcher() {
		return new AntPathMatcher();
	}


	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		EgovWildcardReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new EgovWildcardReloadableResourceBundleMessageSource();
		reloadableResourceBundleMessageSource.setEgovBasenames(
				"classpath:/chungnam/message/message-common",
				"classpath*:chungnam/message/**/*",
				"classpath:/org/egovframe/rte/fdl/idgnr/messages/idgnr",
				"classpath:/org/egovframe/rte/fdl/property/messages/properties");
		reloadableResourceBundleMessageSource.setCacheSeconds(60);
		return reloadableResourceBundleMessageSource;
	}
	
	/**
	 * @return [Resource 설정] 메세지 소스 등록
	 */
	@Bean
	public EgovMessageSource egovMessageSource() {
		EgovMessageSource egovMessageSource = new EgovMessageSource();
		egovMessageSource.setReloadableResourceBundleMessageSource(messageSource());
		return egovMessageSource;
	}
	
	/**
	 * @return [LeaveaTrace 설정] defaultTraceHandler 등록
	 */
	
	@Bean
	public EgovComTraceHandler defaultTraceHandler() {
		return new EgovComTraceHandler();
	}
	

	@Bean
	public DefaultTraceHandleManager traceHandlerService() {
		DefaultTraceHandleManager defaultTraceHandleManager = new DefaultTraceHandleManager();
		defaultTraceHandleManager.setReqExpMatcher(antPathMatcher());
		defaultTraceHandleManager.setPatterns(new String[] {"*"});
		defaultTraceHandleManager.setHandlers(new TraceHandler[] {defaultTraceHandler()});
		return defaultTraceHandleManager;
	}

	@Bean
	public LeaveaTrace leaveaTrace() {
		LeaveaTrace leaveaTrace = new LeaveaTrace();
		leaveaTrace.setTraceHandlerServices(new TraceHandlerService[] {traceHandlerService()});
		return leaveaTrace;
	}

	/**
	 * @return [ImagePaginationRenderer 설정] ImagePaginationRenderer 등록
	 */
	@Bean
	public ImagePaginationRenderer imageRenderer() {
		return new ImagePaginationRenderer();
	}

	/**
	 * @return [ImagePaginationRenderer 설정] defaultPaginationManager 설정.
	 */
	@Bean
	public DefaultPaginationManager paginationManager() {
		DefaultPaginationManager defaultPaginationManager = new DefaultPaginationManager();

		Map<String, PaginationRenderer> rendererType = new HashMap<>();
		rendererType.put("image", imageRenderer());
		defaultPaginationManager.setRendererType(rendererType);

		return defaultPaginationManager;
	}

	/**
	 * @return [MultipartResolver 설정] CommonsMultipartResolver 등록
	 */
	@Bean
	public CommonsMultipartResolver springRegularCommonsMultipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setMaxUploadSize(100000000);
		commonsMultipartResolver.setMaxInMemorySize(100000000);
		return commonsMultipartResolver;
	}

	/**
	 * 확장자 제한 : globals.properties > Globals.fileUpload.Extensions로 설정
	 * @return [MultipartResolver 설정] EgovMultipartResolver 등록
	 */
	@Bean
	public EgovMultipartResolver localMultiCommonsMultipartResolver() {
		EgovMultipartResolver egovMultipartResolver = new EgovMultipartResolver();
		egovMultipartResolver.setMaxUploadSize(100000000);
		egovMultipartResolver.setMaxInMemorySize(100000000);
		return egovMultipartResolver;
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		return localMultiCommonsMultipartResolver();
	}
	
	/**
	 * 암복호화
	 * @return [EgovPasswordEncoder 설정] EgovPasswordEncoder 등록
	 */
	@Bean
	public EgovPasswordEncoder egovPasswordEncoder() {
		EgovPasswordEncoder egovPasswordEncoder = new EgovPasswordEncoder();
		egovPasswordEncoder.setAlgorithm("SHA-256");
		egovPasswordEncoder.setHashedPassword("gdyYs/IZqY86VcWhT8emCYfqY1ahw2vtLG+/FzNqtrQ=");
		return egovPasswordEncoder;
	}
	
	/**
	 * 암복호화
	 * @return [EgovARIACryptoServiceImpl 설정] EgovARIACryptoServiceImpl 등록
	 */
	@Bean
	public EgovARIACryptoServiceImpl egovARIACryptoService() {
		EgovARIACryptoServiceImpl egovARIACryptoServiceImpl = new EgovARIACryptoServiceImpl();
		egovARIACryptoServiceImpl.setPasswordEncoder(egovPasswordEncoder());
		egovARIACryptoServiceImpl.setBlockSize(1024);
		return egovARIACryptoServiceImpl;
	}
	
	@Bean
	public EgovEnvCryptoService egovEnvCryptoService() {
		EgovEnvCryptoService srv = new EgovEnvCryptoServiceImpl();
		//srv.init();
		return srv;
	}
	
	// 실명인증 nextUrl 
	@Bean
	public List<String> egovNextUrlWhitelist() {
		List<String> lst = new ArrayList<>();
		lst.add("/uss/umt/EgovMberSbscrbView.do");
		lst.add("uss/umt/EgovEntrprsMberSbscrbView.do<");
		lst.add("/uss/olh/qna/QnaCnRegistView.do");
		return lst;
	}
	//820. RSS태그관리
	@Bean
	public List<String> egovRSSWhitelist() {
		List<String> lst = new ArrayList<>();
		lst.add("comthtrsmrcvmntrngloginfo");
		lst.add("comtczip<");
		return lst;
	}
	
	/* excel context */
	@Bean 
	public EgovExcelService egovExcelService() {
		EgovExcelService srv = new EgovExcelServiceImpl();
		return srv;
		
	}
	
	@Bean 
	public EgovExcelService excelZipService() throws Exception {
		EgovExcelServiceImpl srv = new EgovExcelServiceImpl();
		srv.setMapClass("egovframework.com.sym.ccm.zip.service.impl.EgovCcmExcelZipMapping");
		srv.setSqlSessionTemplate(sqlSessionTemplate);
		return srv;
		
	}
	
	@Bean 
	public EgovExcelService excelRdnmadZipService() throws Exception {
		EgovExcelServiceImpl srv = new EgovExcelServiceImpl();
		srv.setMapClass("egovframework.com.sym.ccm.zip.service.impl.EgovCcmExcelRdnmadZipMappin");
		srv.setSqlSessionTemplate(sqlSessionTemplate);
		return srv;
		
	}
	
	@Bean 
	public EgovLoginConfig egovLoginConfig()  {
		EgovLoginConfig srv = new EgovLoginConfig();
		srv.setLock(Boolean.parseBoolean(EgovProperties.getProperty("Globals.login.Lock")));
		srv.setLockCount(Integer.parseInt(EgovProperties.getProperty("Globals.login.LockCount")));
		return srv;
		
	}
	
	@Bean("egovUserDetailsSecurityService")
	EgovUserDetailsService egovUserDetailsService() {
		EgovUserDetailsService detailService = new EgovUserDetailsSecurityServiceImpl();
		return detailService;
	}
	
	@Bean("egovUserDetailsHelper")
	EgovUserDetailsHelper egovUserDetailsHelper() {
		EgovUserDetailsHelper egovUserDetailsHelper = new EgovUserDetailsHelper();
		egovUserDetailsHelper.setEgovUserDetailsService(egovUserDetailsService());
		return egovUserDetailsHelper;
	}

}
