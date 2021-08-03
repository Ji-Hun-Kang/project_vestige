package com.jikang.vestige.service;


import com.jikang.vestige.domain.TestRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * 단위테스트(repository등 Service와 관련된 것들만 메모리에 띄우면 됨)
 * TestRepository => 가짜 객체로 만들 수 있음.
 */

@ExtendWith(MockitoExtension.class)
public class TestServiceUnitTest {
    @InjectMocks // TestService객체가 만들어질때 TestServiceUnitTest 파일에 @Mock로 등록된 모든 객체를 주입받는다.
    private TestService testService;

    @Mock
    private TestRepository testRepository;
}
