package com.zhhub.common.lang;

import lombok.Data;

/**
 * @author zql
 * <p>
 * 2022/12/10
 */

@Data
public class Result {

    private int code;
            private String msg;
            private Object data;

            public static Result succ(Object data)
    {
        return succ(200,"操作成功",data);
    }

    public static Result succ(int code, String msg, Object data)
    {
        Result res = new Result();
        res.setCode(code);
        res.setMsg(msg);
        res.setData(data);
        return res;
    }

    public static Result fail(String msg)
    {
        return fail(400,msg,null);
    }


    public static Result fail(int code, String msg, Object data)
    {
        Result res = new Result();
        res.setCode(code);
        res.setMsg(msg);
        res.setData(data);
        return res;
    }




}
