package com.zhhub.security;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zhhub.common.exception.CaptchaException;
import com.zhhub.common.lang.Const;
import com.zhhub.config.RedisConfig;
import com.zhhub.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zql
 * <p>
 * 2023/3/6
 */

@Component
public class CaptchaFiler extends OncePerRequestFilter {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    LoginFailureHandler loginFailureHandler;



    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String url = httpServletRequest.getRequestURI();

        //验证请求地址
        if("/login".equals(url) && httpServletRequest.getMethod().equals("POST"))
        {
            try{
                //验证码逻辑认证
                validata(httpServletRequest);
            }catch (CaptchaException e)
            {
                loginFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
            }
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }


    //验证码逻辑的验证
    private void validata(HttpServletRequest httpServletRequest) {

        String code = httpServletRequest.getParameter("code");
        String key = httpServletRequest.getParameter("token");

        if(StringUtils.isBlank(code) || StringUtils.isBlank(key))
        {
            throw new CaptchaException("验证码错误");
        }


        if(!code.equals(redisUtils.hget(Const.CAPTCHA_KEY,key)))
        {
            throw new CaptchaException("验证码错误");
        }

        //一次性使用验证码
        redisUtils.hdel(Const.CAPTCHA_KEY,key);

    }
}
