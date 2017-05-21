package com.whut.teaching.interceptor;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wpc on 2017/5/21.
 */
public class RequestCounterInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = Logger.getRootLogger();
    private static final Map<String, Long> counts = new HashMap<>();
    protected static org.slf4j.Logger logger = LoggerFactory.getLogger("logger");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();

        logger.debug(uri);

        Long cnt = counts.get(uri);
        if (cnt == null) {
            cnt = 0L;
        }
        cnt++;
        counts.put(uri, cnt);
        log.info("cnt :\t" + cnt + "\t" + uri);
        return super.preHandle(request, response, handler);
    }
}
