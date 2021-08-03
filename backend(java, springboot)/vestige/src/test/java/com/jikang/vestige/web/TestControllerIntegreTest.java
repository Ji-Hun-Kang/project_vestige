package com.jikang.vestige.web;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * 통합테스트(모든 Bean들을 똑같이 IoC 올리고 테스트하는것)
 * WebEnvironment.MOCK = 실제 톰켓을 올리를게 아니라, 다른 톰켓으로 테스트한다.
 * WebEnvironment.RANDOM_POR = 실제 톰켓으로 테스트한다.
 * @AutoConfigureMockMvc MockMvc를 IoC에 등록해준다.
 * @Transactional은 각각의 테스트함수가 종료될 때마다 트랜잭션을 rollback해주는 어노테이션이다.
 */
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TestControllerIntegreTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void save_테스트(){
        Logger logger = LoggerFactory.getLogger(TestControllerIntegreTest.class);

        logger.info("save_테스트 ============================");
    }
}
