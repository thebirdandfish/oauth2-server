package com.revengemission.sso.oauth2.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Program: oauth2-server
 * @Create: 2019-11-20 12:01
 * @Author: JustThink
 * @Description: 处理登出成功的逻辑 Author: JustThink
 * @Include:
 **/
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    public CustomLogoutSuccessHandler(String customLogoutRedirectUrl) {
        this.customLogoutRedirectUrl = customLogoutRedirectUrl;
    }

    private String customLogoutRedirectUrl;

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication)
        throws IOException, ServletException {

        String requestedRedirectUri = request.getParameter("redirectUri");

        if (StringUtils.isNotBlank(requestedRedirectUri)) {
//            第三方应用要求跳转到特定uri
            response.sendRedirect(requestedRedirectUri);
        } else if (StringUtils.isNotBlank(customLogoutRedirectUrl)) {
//            项目中配置了uri
            response.sendRedirect(customLogoutRedirectUrl);

        }else{
//            项目没配置uri，说明本项目不是网页应用
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("退出成功");
        }


    }
}
