package com.zhhub.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author zql
 * <p>
 * 2023/3/6
 */
public class CaptchaException extends AuthenticationException {

    public CaptchaException(String msg) {
        super(msg);
    }
}
