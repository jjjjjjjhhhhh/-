package com.zhhub.security;

import cn.hutool.core.util.StrUtil;
import com.zhhub.entity.SysUser;
import com.zhhub.service.SysUserService;
import com.zhhub.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zql
 * <p>
 * 2023/3/16
 */
public class JwtAuthenticationFiler extends BasicAuthenticationFilter {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserDetailServiceImpl userDetailService;

    @Autowired
    SysUserService sysUserService;

    public JwtAuthenticationFiler(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String jwt = request.getHeader(jwtUtils.getHeader());
        //判断JWT是否为空
        if(StrUtil.isBlankOrUndefined(jwt))
        {
            chain.doFilter(request,response);
            return;
        }


        Claims claims = jwtUtils.getClaimsByToken(jwt);
        if(claims == null)
        {
            throw new JwtException("Token 为空");
        }
        if(jwtUtils.isTokenExpired(claims))
        {
            throw new JwtException("验证过期");
        }

        //获取用户信息
        String username = claims.getSubject();

        SysUser sysUser = sysUserService.getByUsername(username);

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(username,null,userDetailService.getUserAuthority(sysUser.getId()));

        SecurityContextHolder.getContext().setAuthentication(token);

        chain.doFilter(request,response);

    }
}
