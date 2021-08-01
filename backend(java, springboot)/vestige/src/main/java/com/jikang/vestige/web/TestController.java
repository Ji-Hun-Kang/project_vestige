package com.jikang.vestige.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jikang.vestige.domain.Test;
import com.jikang.vestige.service.TestService;

// 스프링이 com.jikang.vestige 패키지 이하를 스캔해서 모든 파일을 메모리에 new하는 것이 아님.
// 특정 어노테이션이 붙어있는 클래스 파일들을 new해서 스프링 컨테이너에 관리한다.(IoC)
@RestController
public class TestController {
	
	private final TestService testService;
	
	@Autowired
	public TestController(TestService testService) {
		this.testService = testService;
	}
	
	@PostMapping("/test")
	public ResponseEntity<?> save(@RequestBody Test test){
		return new ResponseEntity<>(testService.저장하기(test), HttpStatus.CREATED); // 201으로 응답
	}

	@GetMapping("/test")
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(testService.모두가져오기(), HttpStatus.OK); // 200으로 응답
	}

	@GetMapping("/test/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return new ResponseEntity<>(testService.한건가져오기(id), HttpStatus.OK); // 200으로 응답
	}
	
	@PutMapping("/test/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Test test){
		return new ResponseEntity<>(testService.수정하기(id, test), HttpStatus.OK); // 200으로 응답
	}
	
	@DeleteMapping("/test/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id, @RequestBody Test test){
		return new ResponseEntity<>(testService.삭제하기(id), HttpStatus.OK); // 200으로 응답
	}
}
