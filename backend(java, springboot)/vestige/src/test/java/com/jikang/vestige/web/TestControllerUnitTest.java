package com.jikang.vestige.web;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// 단위테스트(Filter, ControllerAdvice 등 Controller 관련 로직만 띄우기)

@WebMvcTest
@ExtendWith(SpringExtension.class)
public class TestControllerUnitTest {

}
