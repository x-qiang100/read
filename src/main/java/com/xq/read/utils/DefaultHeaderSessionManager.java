package com.xq.read.utils;

import com.xq.read.Config.ShiroConfig;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.WebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @author xq
 * @Date 2021/9/9 下午2:16
 * @ClassName DefaultHeaderSessionManager
 * @Description DefaultHeaderSessionManager
 */

public class DefaultHeaderSessionManager extends DefaultSessionManager implements WebSessionManager {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    @Override
    public boolean isServletContainerSessions() {
        return false;
    }

    @Override
    protected Serializable getSessionId(SessionKey sessionKey) {

        logger.info("getSessionId");
        logger.info(sessionKey.getSessionId().toString());
        ServletRequest request = WebUtils.getRequest(sessionKey);
        ServletResponse response = WebUtils.getResponse(sessionKey);
        Object token = request.getAttribute("token");
        return super.getSessionId(sessionKey);
    }



}

