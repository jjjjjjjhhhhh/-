package com.zhhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhhub.entity.SysMenu;
import com.zhhub.entity.SysRole;
import com.zhhub.entity.SysUser;
import com.zhhub.mapper.SysUserMapper;
import com.zhhub.mapper.SysUserRoleMapper;
import com.zhhub.service.SysMenuService;
import com.zhhub.service.SysRoleService;
import com.zhhub.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhhub.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZhongYongqi
 * @since 2022-12-03
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysMenuService sysMenuService;



    @Autowired
    RedisUtils redisUtils;

    @Override
    public SysUser getByUsername(String username) {

        return getOne(new QueryWrapper<SysUser>().eq("username",username));
    }

    @Override
    public String getUserAuthorityInfo(Long userId) {

        SysUser sysUser = sysUserMapper.selectById(userId);

        String authority = "";

        if(redisUtils.hasKey("GrantedAuthority:"+sysUser.getUsername()))
        {
            authority = (String)redisUtils.get("GrantedAuthority:"+sysUser.getUsername());
        }
        else{
            //获取角色
            List<SysRole> roles = sysRoleService.list(new QueryWrapper<SysRole>().inSql("id",
                    "select role_id from sys_user_role where user_id =" +userId ));

            //size>0 代表有多个角色信息 然后对数据进行流处理用 ”，“隔开
            if(roles.size() > 0)
            {
                String roleCodes = roles.stream().map(r -> "ROLE_"+ r.getCode()).collect(Collectors.joining(","));
                authority = roleCodes.concat(",");
            }

            //获取菜单权限
            List<Long> menuId= sysUserMapper.getNavMenuIds(userId);
            if(menuId.size() > 0)
            {
                //在数据库获取用户具体的权限 然后给到authority
                List<SysMenu> menus = sysMenuService.listByIds(menuId);
                String menuPerms =  menus.stream().map(m -> m.getPerms()).collect(Collectors.joining(","));

                authority = authority.concat(menuPerms);
            }

            redisUtils.set("GrantedAuthority:"+sysUser.getUsername(),authority,60*120);
        }



        return authority;
    }

    @Override
    public void clearUserAuthorityInfo(String username) {
        redisUtils.del("GrantedAuthority:" + username);
    }

    @Override
    public void clearUserAuthorityInfoByRoleId(Long roleId) {

    }

    @Override
    public void clearUserAuthorityInfoByMenuId(Long menuId) {

    }

    @Override
    public void addUser(String username, String email, String phone, String statu) {


        sysUserMapper.addUser(username, email, phone, statu);
    }

    @Override
    public void delUser(String id) {
        sysUserMapper.delUser(id);
    }

    @Override
    public void upUser(String username, String email, String phone, String statu, String id) {
        sysUserMapper.upUser(username, email, phone, statu, id);
    }

    @Override
    public void upUserQx(String id, String value) {
        sysUserMapper.upUserQx(id, value);
    }


}
