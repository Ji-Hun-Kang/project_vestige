package com.jikang.vestige.web;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;


// 단위테스트(Filter, ControllerAdvice 등 Controller 관련 로직만 띄우기)

@WebMvcTest
public class TestControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void save_테스트(){
        Logger logger = LoggerFactory.getLogger(TestControllerUnitTest.class);

        logger.info("save_테스트() 시작 ============================");
    }
}
