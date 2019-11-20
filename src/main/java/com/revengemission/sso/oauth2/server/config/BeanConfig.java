package com.revengemission.sso.oauth2.server.config;

import com.revengemission.sso.oauth2.server.properties.CustomSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @Program: oauth2-server
 * @Create: 2019-11-20 12:59
 * @Author: JustThink
 * @Description: 统一处理Bean。如果本Bean不被其他同名Bean覆盖，则注入本Bean。
 * @Include:
 **/
@Configuration
public class BeanConfig {

    @Autowired
    CustomSecurityProperties securityProperties;

    /**
     * 有其他的个性化的LogoutSuccessHandler.class 的Bean，直接注入该同名Bean即可覆盖以下默认Bean。
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new CustomLogoutSuccessHandler(securityProperties.getLogoutSuccessUri());
    }
}
