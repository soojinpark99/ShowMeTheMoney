package com.example.accountbook.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class InvalidJoinProcException extends RuntimeException{
    private static final Logger logger = LoggerFactory.getLogger(InvalidJoinProcException.class);
    public InvalidJoinProcException(String message) {super(message);
    logger.error("회원가입 중 발생한 오류 메세지 : " +message);}
}
