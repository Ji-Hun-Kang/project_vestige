package com.jikang.vestige.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jikang.vestige.service.TestService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// 단위테스트(Filter, ControllerAdvice 등 Controller 관련 로직만 띄우기)

@WebMvcTest
public class TestControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // IoC 환경에 등록됨.
    private TestService testService;

    //BDDMockito 패턴 given, when, then
   @Test
    public void save_테스트() throws Exception {
       Logger logger = LoggerFactory.getLogger(TestControllerUnitTest.class);
       /*logger.info("save_테스트() 시작 ============================");
        com.jikang.vestige.domain.Test test = testService.저장하기(new com.jikang.vestige.domain.Test(null, "제목", "코스"));
        System.out.println("test:"+test);*/
       //given (테스트를 하기 위한 준비)
       com.jikang.vestige.domain.Test test = new com.jikang.vestige.domain.Test(null, "스프링 따라하기", "코스");
       String content = new ObjectMapper().writeValueAsString(test);
       //when(testService.저장하기(test)).thenReturn(new com.jikang.vestige.domain.Test(1L, "스프링 따라하기", "코스"));

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
