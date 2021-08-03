package com.jikang.vestige.web;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.function.Supplier;

// 단위테스트(Filter, ControllerAdvice 등 Controller 관련 로직만 띄우기)

@WebMvcTest
public class TestControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void save_테스트(){

    }
}
