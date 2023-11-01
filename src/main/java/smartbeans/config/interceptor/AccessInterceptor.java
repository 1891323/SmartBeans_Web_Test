package smartbeans.config.interceptor;

import smartbeans.cmmn.LoginVO;
import smartbeans.cmmn.util.EgovUserDetailsHelper;
import smartbeans.config.interceptor.adapter.AbstractUrlMappingHandlerInterceptorAdapter;
import smartbeans.config.uitil.ThreadLocalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 모든 서버접근에 대한 인터셉터
 */
public class AccessInterceptor extends AbstractUrlMappingHandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessInterceptor.class);

    @Override
    public boolean doHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.debug("AccessInterceptor start");
        setThreadLocal(request);
        return true;
    }

    /**
     * 매개변수 전달없이 세션정보를 바로 쓸 수 있도록 ThreadLocal 변수에 필요한 정보를 담음
     *
     * @param request
     */
    public void setThreadLocal(HttpServletRequest request) {


        if (true) {
            LOGGER.debug("***** 개발용 로그인 정보가 주입되었습니다. *****");
            LOGGER.debug("***** 개발용 로그인 정보가 주입되었습니다. *****");
            LOGGER.debug("***** 개발용 로그인 정보가 주입되었습니다. *****");
            LOGGER.debug("***** 개발용 로그인 정보가 주입되었습니다. *****");
            LOGGER.debug("***** 개발용 로그인 정보가 주입되었습니다. *****");
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.sessUserSe, "S");
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.sessUserId, "admin");
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.sessUserNm, "개발자");
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.sessEmail, "test@test.com");
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.requestUri, request.getRequestURI());
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.transactionId, UUID.randomUUID().toString().replaceAll("-", ""));
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.serverName, request.getServerName());
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.userIp, request.getRemoteAddr());
            return;
        }

        if (EgovUserDetailsHelper.isAuthenticated()) {
            LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.sessUserSe, loginVO.getUserSe());
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.sessUserId, loginVO.getId());
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.sessUserNm, loginVO.getName());
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.sessEmail, loginVO.getEmail());
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.requestUri, request.getRequestURI());
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.transactionId, UUID.randomUUID().toString().replaceAll("-", ""));
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.serverName, request.getServerName());
            ThreadLocalUtil.set(ThreadLocalUtil.KEY.userIp, request.getRemoteAddr());
        } else {
            ThreadLocalUtil.destroy();
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }


}
