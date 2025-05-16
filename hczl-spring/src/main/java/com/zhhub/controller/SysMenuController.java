package com.zhhub.controller;


import cn.hutool.core.map.MapUtil;
import com.zhhub.common.lang.Result;
import com.zhhub.dto.SysMenuDto;
import com.zhhub.entity.SysMenu;
import com.zhhub.entity.SysUser;
import com.zhhub.service.SysMenuService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZhongYongqi
 * @since 2022-12-03
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {

    @GetMapping("/nav")
    public  Result nav(Principal principal)
    {
        SysUser sysUser = sysUserService.getByUsername(principal.getName());

        //获取权限信息
        String authorityInfo = sysUserService.getUserAuthorityInfo(sysUser.getId());
        String[] authorityInfoArray = StringUtils.tokenizeToStringArray(authorityInfo, ",");

        //获取导航栏信息
        List<SysMenuDto> navs = sysMenuService.getCurrentUserNav();


        return Result.succ(MapUtil.builder()
                .put("authoritys",authorityInfoArray)
                .put("nav",sysMenuService.getCurrentUserNav())
                .map()
        );

    }


    @GetMapping("/info/{id}")
    public Result info(@PathVariable(name = "id")Long id)
    {
        return Result.succ(sysMenuService.getById(id));
    }

    @GetMapping("/list")
    public Result list()
    {
        List<SysMenu> menus = sysMenuService.tree();
        return null;
    }



}
