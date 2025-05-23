package com.zhhub.security;

import cn.hutool.json.JSONUtil;
import com.zhhub.common.lang.Result;
import com.zhhub.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zql
 * <p>
 * 2023/6/3
 */

@Component
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        if(authentication != null)
        {
            new SecurityContextLogoutHandler().logout(httpServletRequest,httpServletResponse,authentication);
        }

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();


        httpServletResponse.setHeader(jwtUtils.getHeader(),"");

        //成功返回结果
        Result result = Result.succ("退出成功");

        outputStream.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();

    }
}
