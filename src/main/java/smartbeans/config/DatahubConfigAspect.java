package smartbeans.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.util.AntPathMatcher;

import smartbeans.cmmn.AopExceptionTransfer;
import smartbeans.cmmn.DatahubExcepHndlr;
import smartbeans.cmmn.DatahubOthersExcepHndlr;

import org.egovframe.rte.fdl.cmmn.aspect.ExceptionTransfer;
import org.egovframe.rte.fdl.cmmn.exception.handler.ExceptionHandler;
import org.egovframe.rte.fdl.cmmn.exception.manager.DefaultExceptionHandleManager;
import org.egovframe.rte.fdl.cmmn.exception.manager.ExceptionHandlerService;

@Configuration
@EnableAspectJAutoProxy
public class DatahubConfigAspect {

	@Autowired
	AntPathMatcher antPathMatcher;

	@Bean
	public DatahubExcepHndlr datahubHandler() {
		DatahubExcepHndlr datahubHandler = new DatahubExcepHndlr();
		return datahubHandler;
	}

	@Bean
	public DatahubOthersExcepHndlr otherHandler() {
		DatahubOthersExcepHndlr egovSampleOthersExcepHndlr = new DatahubOthersExcepHndlr();
		return egovSampleOthersExcepHndlr;
	}

	@Bean
	public DefaultExceptionHandleManager defaultExceptionHandleManager(DatahubExcepHndlr datahubHandler) {
		DefaultExceptionHandleManager defaultExceptionHandleManager = new DefaultExceptionHandleManager();
		defaultExceptionHandleManager.setReqExpMatcher(antPathMatcher);
		defaultExceptionHandleManager.setPatterns(new String[] {"**service.impl.*"});
		defaultExceptionHandleManager.setHandlers(new ExceptionHandler[] {datahubHandler});
		return defaultExceptionHandleManager;
	}

	@Bean
	public DefaultExceptionHandleManager otherExceptionHandleManager() {
		DefaultExceptionHandleManager defaultExceptionHandleManager = new DefaultExceptionHandleManager();
		defaultExceptionHandleManager.setReqExpMatcher(antPathMatcher);
		defaultExceptionHandleManager.setPatterns(new String[] {"**service.impl.*"});
		defaultExceptionHandleManager.setHandlers(new ExceptionHandler[] {otherHandler()});
		return defaultExceptionHandleManager;
	}

	@Bean
	public ExceptionTransfer exceptionTransfer(
		@Qualifier("defaultExceptionHandleManager") DefaultExceptionHandleManager defaultExceptionHandleManager,
		@Qualifier("otherExceptionHandleManager") DefaultExceptionHandleManager otherExceptionHandleManager) {
		ExceptionTransfer exceptionTransfer = new ExceptionTransfer();
		exceptionTransfer.setExceptionHandlerService(new ExceptionHandlerService[] {
			defaultExceptionHandleManager, otherExceptionHandleManager
		});
		return exceptionTransfer;
	}

	@Bean
	public AopExceptionTransfer aopExceptionTransfer(ExceptionTransfer exceptionTransfer) {
		AopExceptionTransfer aopExceptionTransfer = new AopExceptionTransfer();
		aopExceptionTransfer.setExceptionTransfer(exceptionTransfer);
		return aopExceptionTransfer;
	}

}
