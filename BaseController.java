package com.zhhub.controller;

import com.zhhub.service.*;
import com.zhhub.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zql
 * <p>
 * 2022/12/3
 */

public class BaseController {

    @Autowired
    HttpServletRequest req;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysMenuService sysMenuService;

    @Autowired
    SysUserRoleService sysUserRoleService;

    @Autowired
    SysRoleMenuService sysRoleMenuService;



    @Autowired
    UploadAvatarController uploadAvatarController;


}
