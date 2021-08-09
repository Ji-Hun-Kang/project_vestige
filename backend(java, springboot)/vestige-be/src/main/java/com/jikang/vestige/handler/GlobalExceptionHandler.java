package com.jikang.vestige.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // exception이 발생하면 해당 클래스가 호출된다.
@RestController
public class GlobalExceptionHandler {

    // Exception이 발생하면 Spring은 아래의 함수에 전달한다.
    @ExceptionHandler(value = Exception.class)
    public String handleArgumentException(Exception e){
        return "<h1>"+e.getMessage()+"</h1>";
    }
}
