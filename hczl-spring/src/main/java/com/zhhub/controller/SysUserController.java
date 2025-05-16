package com.zhhub.controller;


import com.zhhub.common.lang.Result;
import com.zhhub.entity.SysUser;
import com.zhhub.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;



@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {

    @Autowired
    SysUserService sysUserService;

    @GetMapping("/getUserInfo")
    public Result getUserInfo()
    {
        return Result.succ(sysUserService.list());
    }

    @GetMapping("/addUser")
    public Result addUser(String username, String email, String phone, String statu)
    {
        sysUserService.addUser(username, email, phone, statu);
        return null;
    }


    @GetMapping("/delUser")
    public Result delUser(String id)
    {
        sysUserService.delUser(id);
        return null;
    }

    @GetMapping("/upUser")
    public Result upUser(String username, String email, String phone, String statu,String id)
    {
        sysUserService.upUser(username, email, phone, statu, id);
        return null;
    }

    @GetMapping("/upUserQx")
    public Result upUserQx(String id,String value)
    {
        sysUserService.upUserQx(id, value);
        return null;
    }

    @GetMapping("/userList")
    public Result userList(){
        return Result.succ(sysUserService.list());
    }



}
