package com.jikang.vestige.repository;

import com.jikang.vestige.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// <User, Integer> = User 테이블을 관리하는 레파지토리이며, User테이블의 기본키는 Integer임을 의미
// DAO와 비슷하다.
// 자동으로 bean으로 등록이 된다. @Repository를 생략하는 것이 가능하다.
public interface UserRepository extends JpaRepository<User, Integer> {
}
