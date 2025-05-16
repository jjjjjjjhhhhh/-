package com.zhhub.controller;


import cn.hutool.core.map.MapUtil;
import com.google.code.kaptcha.Producer;
import com.zhhub.common.lang.Const;
import com.zhhub.common.lang.Result;
import com.zhhub.entity.SysUser;
import com.zhhub.service.SysUserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.UUID;

/**
 * @author zql
 * <p>
 * 2023/2/4
 */

@RestController
public class AuthController extends BaseController {

    @Autowired
    Producer producer;

    @GetMapping("/captcha")
    public Result captcha() throws IOException
    {
        String key = UUID.randomUUID().toString();
        String code  = producer.createText();
        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image,"jpg",outputStream);

        BASE64Encoder encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";

        String base64Img = str + encoder.encode(outputStream.toByteArray());

        redisUtils.hset(Const.CAPTCHA_KEY,key,code,120);

        return Result.succ(
                MapUtil.builder()
                .put("token",key)
                .put("captchIma",base64Img)
                .build()
        );



    }


    //获取用户信息接口
    @GetMapping("/sys/userInfo")
    public  Result userInfo(Principal principal)
    {
        SysUser sysUser = sysUserService.getByUsername(principal.getName());

        return Result.succ(MapUtil.builder()
                .put("id",sysUser.getId())
                .put("username",sysUser.getUsername())
                .put("avatar",sysUser.getAvatar())
                .put("created",sysUser.getCreated())
                .map());
    }






}
