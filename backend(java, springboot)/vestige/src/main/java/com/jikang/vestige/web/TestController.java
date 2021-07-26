package com.jikang.vestige.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 스프링이 com.jikang.vestige 패키지 이하를 스캔해서 모든 파일을 메모리에 new하는 것이 아님.
// 특정 어노테이션이 붙어있는 클래스 파일들을 new해서 스프링 컨테이너에 관리한다.(IoC)
@RestController
public class TestController {
	
	@GetMapping("/")
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<String>("Hello Spring Boot Server", HttpStatus.OK);
	}

}
