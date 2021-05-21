package com.ny.lg.common.handler;

import com.ny.lg.common.constants.Result;
import com.ny.lg.common.exception.LgException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @version 1.0.0
 * @author： guog
 * @date 2021/5/20 17:38
 * @description
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return Result.error(500, e.getMessage());
    }

    /**
     * 业务处理异常情况
     *
     * @param lg
     * @return
     */
    @ExceptionHandler(LgException.class)
    @ResponseStatus(HttpStatus.OK)
    Result handleException(LgException lg) {
        logger.error(lg.getMessage(), lg);
        return Result.error(lg.getBizCode().getCode(), lg.getBizCode().getMessage());
    }

    /**
     * Validated异常
     *
     * @param argVaid
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Result handleException(MethodArgumentNotValidException argVaid) {
        logger.error(argVaid.getMessage(), argVaid);
        return Result.error(400, argVaid.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }
}