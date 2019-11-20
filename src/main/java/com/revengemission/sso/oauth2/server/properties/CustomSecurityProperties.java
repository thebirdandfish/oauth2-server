package com.revengemission.sso.oauth2.server.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Program: oauth2-server
 * @Create: 2019-11-20 12:42
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Component
@ConfigurationProperties(prefix = "custom.security")
public class CustomSecurityProperties {
    private String logoutSuccessUri;

    public String getLogoutSuccessUri() {
        return logoutSuccessUri;
    }

    public void setLogoutSuccessUri(String logoutSuccessUri) {
        this.logoutSuccessUri = logoutSuccessUri;
    }
}
