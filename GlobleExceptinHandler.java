package com.zhhub.common.exception;

import com.zhhub.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zql
 * <p>
 * 2022/12/22
 */
@Slf4j
@RestControllerAdvice
public class GlobleExceptinHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result handle(IllegalArgumentException e)
    {
        log.error("Assert:",e.getMessage());
        return Result.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Result handle(RuntimeException e)
    {
        log.error("运行时异常:",e.getMessage());
        return Result.fail(e.getMessage());
    }

}
