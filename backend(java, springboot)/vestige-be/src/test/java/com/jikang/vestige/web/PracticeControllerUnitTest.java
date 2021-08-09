package com.jikang.vestige.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jikang.vestige.model.Practice;
import com.jikang.vestige.service.PracticeService;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

// 단위테스트(Filter, ControllerAdvice 등 Controller 관련 로직만 띄우기)

@WebMvcTest
public class PracticeControllerUnitTest {

    Logger logger = LoggerFactory.getLogger(PracticeControllerUnitTest.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean // IoC 환경에 bean 등록됨.(가짜 TestService)
    private PracticeService practiceService;

    //BDDMockito 패턴 given, when, then
    @Test
    public void save_테스트() throws Exception {
        Logger logger = LoggerFactory.getLogger(PracticeControllerUnitTest.class);

        /*logger.info("save_테스트() 시작 ============================");
        // 가짜 TestService를 사용하므로 실제로 저장하기 작업을 수행하지 않는다.
        Practice practice = practiceService.저장하기(new Practice(null, "제목", "코스"));
        System.out.println("test:"+test);*/

       //given (테스트를 하기 위한 준비)
       Practice practice = new Practice(null, "스프링 따라하기", "코스");
       // ObjectMapper().writeValueAsString(object) : object를 json으로 변경하는 함수
       // ObjectMapper().readValue(json) : json을 object로 변경하는 함수
       String content = new ObjectMapper().writeValueAsString(practice);
       //logger.info(content);
       when(practiceService.저장하기(practice)).thenReturn(new Practice(1L, "스프링 따라하기", "jikang"));

        /*try{

            when(practiceService.저장하기(practice)).thenReturn(new Practice(1L, "스프링 따라하기", "jikang"));


        }catch(Exception exception){
            logger.error(exception.getMessage());
        }*/

       // when(테스트 실행)
       ResultActions resultAction = mockMvc.perform(post("/practice")
               .contentType(MediaType.APPLICATION_JSON_UTF8)
               .content(content)
               .accept(MediaType.APPLICATION_JSON_UTF8));

       // then(검증)
       resultAction
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.title").value("스프링부트 따라하기"))
               .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void findAll_테스트() throws Exception{
        //given
        List<Practice> books = new ArrayList<>();
        books.add(new Practice(1L, "스프링부트 따라하기", "jikang"));
        books.add(new Practice(2L, "리엑트 따라하기", "jikang"));

        when(practiceService.모두가져오기()).thenReturn(books);

        //when
        ResultActions resultAction = mockMvc.perform(get("/practice")
                .accept(MediaType.APPLICATION_JSON_UTF8));

        //then
        resultAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$.[0].title").value("스프링부트 따라하기"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void findById_테스트() throws Exception{
        // given
        Long id = 1L;
        when(practiceService.한건가져오기(id)).thenReturn(new Practice(1L, "자바 공부하기", "쌀"));

        // when
        ResultActions resultAction = mockMvc.perform(get("/practice/{id}", id)
                .accept(MediaType.APPLICATION_JSON_UTF8));

        // then
        resultAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("자바 공부하기"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void update_테스트() throws Exception{
        // given
        Long id = 1L;
        Practice practice = new Practice(null, "C++ 따라하기", "코스");
        String content = new ObjectMapper().writeValueAsString(practice);

        when(practiceService.수정하기(id, practice)).thenReturn(new Practice(1L, "C++ 따라하기", "코스"));

        // when(테스트 실행)
        ResultActions resultAction = mockMvc.perform(put("/practice/{id}", id)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
                .accept(MediaType.APPLICATION_JSON_UTF8));


        // then
        resultAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("C++ 따라하기"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void delete_테스트() throws Exception{
        // given
        Long id = 1L;

        when(practiceService.삭제하기(id)).thenReturn("ok");

        // when(테스트 실행)
        ResultActions resultAction = mockMvc.perform(delete("/practice/{id}", id)
                .accept(MediaType.TEXT_PLAIN));


        // then
        resultAction
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        MvcResult requestResult = resultAction.andReturn();
        String result = requestResult.getResponse().getContentAsString();

        assertEquals("ok", result);
    }
}
