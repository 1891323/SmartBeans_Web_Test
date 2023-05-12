package chungnam.config.interceptor.adapter;

import org.egovframe.rte.fdl.cmmn.exception.BaseException;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractUrlMappingHandlerInterceptorAdapter extends HandlerInterceptorAdapter {

    List<String> skipUrlRegexPatterns;
    List<String> allowUrlRegexPatterns;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isMatchedUrls(request)) {
            return doHandle(request, response, handler);
        }
        return true;
    }

    public abstract boolean doHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;

    private boolean isMatchedUrls(HttpServletRequest request) throws BaseException {
        if (skipUrlRegexPatterns != null && allowUrlRegexPatterns != null) {
            throw new BaseException("skipUrl and applyUrl in interceptor property cannot be used at the same time");
        } else if ((skipUrlRegexPatterns != null && matches(skipUrlRegexPatterns, request.getServletPath()))
                || (allowUrlRegexPatterns != null && !matches(allowUrlRegexPatterns, request.getServletPath()))) {
            return false;
        }
        return true;

    }

    private boolean matches(List<String> skipUrlRegexPatterns, String servletPath) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for (String skipUrlRegexPattern : skipUrlRegexPatterns) {
            if (antPathMatcher.match(skipUrlRegexPattern, servletPath)) {
                return true;
            }
        }
        return false;
    }

    private Comparator[] getComparators(List<String> skipUrlRegexPatterns) {
        AntPathMatcher[] patterns = new AntPathMatcher[skipUrlRegexPatterns.size()];
        Comparator[] comparators = new Comparator[skipUrlRegexPatterns.size()];
        for (int i = 0; i < skipUrlRegexPatterns.size(); i++) {
            patterns[i] = new AntPathMatcher();
            patterns[i].setTrimTokens(true);
            comparators[i] = patterns[i].getPatternComparator(skipUrlRegexPatterns.get(i));
        }
        return comparators;
    }

    public void setSkipUrlRegexPatterns(List<String> skipUrlRegexPatterns) {
        this.skipUrlRegexPatterns = skipUrlRegexPatterns;
    }

    public void setAllowUrlRegexPatterns(List<String> allowUrlRegexPatterns) {
        this.allowUrlRegexPatterns = allowUrlRegexPatterns;
    }
}
