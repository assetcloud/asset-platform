package com.asset.exception;

/**
 * 不算真正的异常，只是为了从service中抛数据给controller
 * @author YBY
 */
public class InfoException extends RuntimeException{

    public InfoException(String message) {
        super(message);
    }
}
