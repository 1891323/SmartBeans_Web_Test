package chungnam.config.uitil;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <pre>
 * 사용자 정보 전파용 thread 정보 저장 클래스.
 * </pre>
 */
public class ThreadLocalUtil {

    /**
     * ThreadLocal.
     */
    private static ThreadLocal<ConcurrentHashMap<String, Object>> threadLocal = new ThreadLocal<ConcurrentHashMap<String, Object>>();

    /**
     * ThreadLocal 저장 대상 Key.
     */
    public enum KEY {
        sessUserSe, sessUserId, sessUserNm, sessEmail, requestUri, transactionId, userIp, serverName
    }

    /**
     * ThreadLocal 변수 초기화.
     */
    public static void init(){

        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<String, Object>();
        threadLocal.set(map);
    }

    /**
     * ThreadLocal 변수 삭제.
     */
    public static void destroy(){

        threadLocal.remove();
    }

    /**
     * ThreadLocal 초기화 여부.
     *
     * @return isInitialized
     */
    public static boolean isInitialized(){

        return (threadLocal.get() != null);
    }

    /**
     * ThreadLocal에 정보 저장.
     *
     * @param key
     * @param value
     */
    public static void set(KEY key, Object value){

        if (!ThreadLocalUtil.isInitialized()) {
            ThreadLocalUtil.init();
        }
        ConcurrentHashMap<String, Object> map = threadLocal.get();
        if(map != null && key != null && value != null)
            map.put(key.name(), value);
    }

    /**
     * ThreadLocal Value 취득.
     *
     * @param key
     * @return
     */
    public static Object get(KEY key){

        ConcurrentHashMap<String, Object> map = threadLocal.get();
        if (map == null) {
            return null;
        }
        return map.get(key.name());
    }

    /**
     * ThreadLocal Value 취득.
     *
     * @param key
     * @return
     */
    public static int getInt(KEY key, int defaultValue){

        ConcurrentHashMap<String, Object> map = threadLocal.get();
        if (map == null) {
            return defaultValue;
        }
        Object obj = map.get(key.name());
        if (obj instanceof Integer) {
            return (Integer)obj;
        } else {
            return defaultValue;
        }
    }

    /**
     * <pre>
     * ThreadLocal Value 취득.
     * Key 해당 항목이 존재하지 않는 경우 파라메타의 defaultStr 반환.
     * </pre>
     *
     * @param key
     * @param defaultStr
     *            디폴트 문자열
     * @return
     */
    public static String getString(KEY key, String defaultStr){

        return StringUtils.defaultIfEmpty((String)ThreadLocalUtil.get(key), defaultStr);
    }
}
