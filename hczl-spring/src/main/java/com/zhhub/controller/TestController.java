package com.zhhub.controller;

import com.zhhub.common.lang.Result;

import com.zhhub.entity.SysMenu;

import com.zhhub.service.SysMenuService;
import com.zhhub.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zql
 * <p>
 * 2022/12/3
 */

@RestController
public class TestController {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysMenuService sysMenuService;



    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    //下面的方法只允许admin超级管理员进行访问
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/test")
    public Result test()
    {
        return Result.succ(sysUserService.list());
    }

    //只允许角色的权限信息里面有“sys:user:list”权限的时候访问
    @PreAuthorize("hasAuthority('sys:user:list')")
    @GetMapping("/test/pass")
    public Result pass()
    {
        String password = bCryptPasswordEncoder.encode("111111");
        boolean match = bCryptPasswordEncoder.matches("111111",password);

        System.out.println("匹配结果："+match);

        return Result.succ(password);
    }


}
