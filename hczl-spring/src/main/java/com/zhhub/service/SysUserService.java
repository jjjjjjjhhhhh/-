package com.zhhub.service;

import com.zhhub.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZhongYongqi
 * @since 2022-12-03
 */
public interface SysUserService extends IService<SysUser> {

    SysUser getByUsername(String username);

    String getUserAuthorityInfo(Long userId);

    void clearUserAuthorityInfo(String usernamne);
    void clearUserAuthorityInfoByRoleId(Long roleId);
    void clearUserAuthorityInfoByMenuId(Long menuId);
    void addUser(String username,String email,String phone,String statu);
    void delUser(String  id);
    void upUser(String username,String email,String phone,String statu,String id);
    void upUserQx(String id,String value);
}
