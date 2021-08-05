package com.jikang.vestige.domain;
// 단위테스트(DB관련된 Bean이 IoC에 등록되면 됨)

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
// Replace.ANY는 가짜 데이터베이스로 테스트한다, Replace.NONE은 실제 데이터베이스로 테스트한다.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DataJpaTest // Repository들을 다 IoC에 등록함.
public class PracticeRepositoryUnitTest {

    @Autowired
    private PracticeRepository bookRepository;
}
