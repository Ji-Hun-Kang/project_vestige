package com.jikang.vestige.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jikang.vestige.service.TestService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

   /* @Test
    public void save_테스트(){
        Logger logger = LoggerFactory.getLogger(TestControllerIntegreTest.class);
        logger.info("save_테스트 ============================");
    }*/

    //BDDMockito 패턴 given, when, then
    @Test
    public void save_테스트() throws Exception {
        Logger logger = LoggerFactory.getLogger(TestControllerUnitTest.class);

        /*logger.info("save_테스트() 시작 ============================");
        // 가짜 TestService를 사용하므로 실제로 저장하기 작업을 수행하지 않는다.
        com.jikang.vestige.domain.Test test = testService.저장하기(new com.jikang.vestige.domain.Test(null, "제목", "코스"));
        System.out.println("test:"+test);*/

        //given (테스트를 하기 위한 준비)
        com.jikang.vestige.domain.Test test = new com.jikang.vestige.domain.Test(null, "스프링 따라하기", "코스");
        // ObjectMapper().writeValueAsString(object) : object를 json으로 변경하는 함수
        // ObjectMapper().readValue(json) : json을 object로 변경하는 함수
        String content = new ObjectMapper().writeValueAsString(test);
        //logger.info(content);
        //when(testService.저장하기(test)).thenReturn(new com.jikang.vestige.domain.Test(1L, "스프링 따라하기", "jikang"));

        // when(테스트 실행)
        ResultActions resultAction = mockMvc.perform(post("/test")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
                .accept(MediaType.APPLICATION_JSON_UTF8));

        // then(검증)
        resultAction
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("스프링 따라하기"))
                .andDo(MockMvcResultHandlers.print());
    }
}
