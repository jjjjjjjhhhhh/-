package com.zhhub.mapper;

import com.zhhub.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ZhongYongqi
 * @since 2022-12-03
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<Long> getNavMenuIds(Long userId);

    void addUser(String username,String email,String phone,String statu);

    void delUser(String  id);

    void upUser(String username,String email,String phone,String statu,String id);

    void upUserQx(String id,String value);

}
