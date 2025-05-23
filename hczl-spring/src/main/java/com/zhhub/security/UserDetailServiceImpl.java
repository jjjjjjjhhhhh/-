package com.zhhub.security;

import com.zhhub.entity.SysUser;
import com.zhhub.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zql
 * <p>
 * 2023/3/19
 */

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser sysUser = sysUserService.getByUsername(username);
        if(sysUser == null)
        {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        return new AccountUser(sysUser.getId(),sysUser.getUsername(),sysUser.getPassword(),getUserAuthority(sysUser.getId()));
    }

    //获取用户权限信息
    public List<GrantedAuthority> getUserAuthority(Long userId)
    {
        //角色 菜单操作权限
        String authority = sysUserService.getUserAuthorityInfo(userId);

        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
    }
}
