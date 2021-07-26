package com.jikang.vestige.service;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jikang.vestige.domain.Test;
import com.jikang.vestige.domain.TestRepository;

@Service // 기능을 정의할 수 있고, 트랜잭션을 관리할 수 있다.
public class TestService {
	
	private final TestRepository testRepository;
	
	@Autowired
	public TestService(TestRepository testRepository) {
		this.testRepository = testRepository;
	}
	
	@Transactional // 서비스 함수가 종료될 때 commit할지 rollback할지 트랜잭션을 관리한다.
	public Test 저장하기(Test test) {
		return testRepository.save(test);
	}
	
	// JPA 변경감지라는 내부 기능을 활성화하지 않는다.
	// update시의 정합성을 유지해준다. insert의 유령데이터현상(팬텀현상)을 못막는다.	
	@Transactional(readOnly = true)
	public Test 한건가져오기(Long id) {
		return testRepository.findById(id)
				.orElseThrow(()->new IllegalArgumentException("id를 확인해주세요!"));
				/*.orElseThrow(new Supplier<IllegalArgumentException>() {
					@Override
					public IllegalArgumentException get() {
						return new IllegalArgumentException("id를 확인해주세요!");
					}
				});*/
	}
	
	@Transactional(readOnly = true)
	public List<Test> 모두가져오기(){
		return testRepository.findAll();
	}
	
	@Transactional
	public Test 수정하기(Long id, Test test) {
		// 더티체팅 update치기
		Test testEntity = testRepository.findById(id)
				.orElseThrow(()->new IllegalArgumentException("id를 확인해주세요!")); // 영속화 -> 영속성 컨텍스트에 보관
		testEntity.setTitle(test.getTitle());
		testEntity.setAuthor(test.getAuthor());
		return testEntity;
	} // 함수의 종료 = 트랜잭션 종료 => 영속화 되어있는 데이터를 DB로 갱신한다.(flush) => commit =======> 더티체킹
	
	@Transactional
	public String 삭제하기(Long id) {
		testRepository.deleteById(id); // 오류 발생시 Exception을 타니까 신경쓰지 말고.. 나중에 처리..
		return "ok";
	}
}
