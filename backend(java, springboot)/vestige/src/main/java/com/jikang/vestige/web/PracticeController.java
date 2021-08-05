package com.jikang.vestige.web;

import com.jikang.vestige.domain.Practice;
import com.jikang.vestige.service.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 스프링이 com.jikang.vestige 패키지 이하를 스캔해서 모든 파일을 메모리에 new하는 것이 아님.
// 특정 어노테이션이 붙어있는 클래스 파일들을 new해서 스프링 컨테이너에 관리한다.(IoC)
@RestController
public class PracticeController {
	
	private final PracticeService practiceService;
	
	@Autowired
	public PracticeController(PracticeService practiceService) {
		this.practiceService = practiceService;
	}

	@GetMapping("/")
	public ResponseEntity<?> checkRunOfServer(){
		return new ResponseEntity<>("서버동작확인", HttpStatus.OK);
	}

	@PostMapping("/practice")
	public ResponseEntity<?> save(@RequestBody Practice practice){
		return new ResponseEntity<>(practiceService.저장하기(practice), HttpStatus.CREATED); // 201으로 응답
	}

	@GetMapping("/practice")
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(practiceService.모두가져오기(), HttpStatus.OK); // 200으로 응답
	}

	@GetMapping("/practice/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return new ResponseEntity<>(practiceService.한건가져오기(id), HttpStatus.OK); // 200으로 응답
	}
	
	@PutMapping("/practice/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Practice practice){
		return new ResponseEntity<>(practiceService.수정하기(id, practice), HttpStatus.OK); // 200으로 응답
	}
	
	@DeleteMapping("/practice/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		return new ResponseEntity<>(practiceService.삭제하기(id), HttpStatus.OK); // 200으로 응답
	}
}
