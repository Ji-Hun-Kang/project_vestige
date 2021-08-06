package com.jikang.vestige.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jikang.vestige.model.Practice;
import com.jikang.vestige.repository.PracticeRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
public class PracticeControllerIntegreTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PracticeRepository practiceRepository;

    @Autowired
    private EntityManager entityManager; // jpa가 구현하는것? jpa의 구현체?

    // 각 테스트가 진행될 때마다 인크리먼트를 초기화 시켜준다.
    @BeforeEach // junit4 = @before
    public void init(){
       /* List<Book> books = new ArrayList<>();
        books.add(new Book(null, "스프링부트 따라하기", "jikang"));
        books.add(new Book(null, "리엑트 따라하기", "jikang"));
        books.add(new Book(null, "Junit 따라하기", "jikang"));

        bookRepository.saveAll(books);*/
        //entityManager.persist(new Book());
        entityManager.createNativeQuery("ALTER TABLE book AUTO_INCREMENT = 1").executeUpdate();
    }

   /* @AfterEach
    public void end(){
        bookRepository.deleteAll();
    }*/

   /* @Test
    public void save_테스트(){
        Logger logger = LoggerFactory.getLogger(PracticeControllerIntegreTest.class);
        logger.info("save_테스트 ============================");
    }*/

    //BDDMockito 패턴 given, when, then
    @Test
    public void save_테스트() throws Exception {
        Logger logger = LoggerFactory.getLogger(PracticeControllerUnitTest.class);

        /*logger.info("save_테스트() 시작 ============================");
        // 가짜 BookService를 사용하므로 실제로 저장하기 작업을 수행하지 않는다.
        com.jikang.vestige.model.Book book = PracticeService.저장하기(new Book(null, "제목", "코스"));
        System.out.println("book:"+book);*/

        //given (테스트를 하기 위한 준비)
        Practice book = new Practice(null, "스프링 따라하기", "코스");
        // ObjectMapper().writeValueAsString(object) : object를 json으로 변경하는 함수
        // ObjectMapper().readValue(json) : json을 object로 변경하는 함수
        String content = new ObjectMapper().writeValueAsString(book);
        //logger.info(content);
        //when(bookService.저장하기(book)).thenReturn(new Book(1L, "스프링 따라하기", "jikang"));

        // when(테스트 실행)
        ResultActions resultAction = mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
                .accept(MediaType.APPLICATION_JSON_UTF8));

        // then(검증)
        resultAction
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("스프링 따라하기"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void findAll_테스트() throws Exception{
        //given
        List<Practice> books = new ArrayList<>();
        books.add(new Practice(null, "스프링부트 따라하기", "jikang"));
        books.add(new Practice(null, "리엑트 따라하기", "jikang"));
        books.add(new Practice(null, "Junit 따라하기", "jikang"));

        practiceRepository.saveAll(books);

        //when
        ResultActions resultAction = mockMvc.perform(get("/book")
                .accept(MediaType.APPLICATION_JSON_UTF8));

        //then
        resultAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$.[1].title").value("Junit 따라하기"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void findById_테스트() throws Exception{
        // given
        Long id = 2L;

        List<Practice> books = new ArrayList<>();
        books.add(new Practice(null, "스프링부트 따라하기", "jikang"));
        books.add(new Practice(null, "리엑트 따라하기", "jikang"));
        books.add(new Practice(null, "Junit 따라하기", "jikang"));
        practiceRepository.saveAll(books);

        // when
        ResultActions resultAction = mockMvc.perform(get("/book/{id}", id)
                .accept(MediaType.APPLICATION_JSON_UTF8));

        // then
        resultAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("리엑트 따라하기"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void update_테스트() throws Exception{
        // given
        Long id = 3L;
        List<Practice> books = new ArrayList<>();
        books.add(new Practice(null, "스프링부트 따라하기", "jikang"));
        books.add(new Practice(null, "리엑트 따라하기", "jikang"));
        books.add(new Practice(null, "Junit 따라하기", "jikang"));

        practiceRepository.saveAll(books);

        Practice book = new Practice(null, "C++ 따라하기", "코스");
        String content = new ObjectMapper().writeValueAsString(book);



        // when(테스트 실행)
        ResultActions resultAction = mockMvc.perform(put("/book/{id}", id)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
                .accept(MediaType.APPLICATION_JSON_UTF8));


        // then
        resultAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.title").value("C++ 따라하기"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void delete_테스트() throws Exception{
        // given
        Long id = 4L;

        List<Practice> books = new ArrayList<>();
        books.add(new Practice(null, "스프링부트 따라하기", "jikang"));
        books.add(new Practice(null, "리엑트 따라하기", "jikang"));
        books.add(new Practice(null, "Junit 따라하기", "jikang"));

        practiceRepository.saveAll(books);

        // when(테스트 실행)
        ResultActions resultAction = mockMvc.perform(delete("/book/{id}", id)
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
