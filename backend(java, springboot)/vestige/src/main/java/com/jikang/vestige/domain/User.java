package com.jikang.vestige.domain;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

//ORM = Object -> 테이블로 맵핑하는 기술
@Entity //서버 실행시에 테이블이 데이터베이스에 생성됨.(ORM)
public class User {

    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에 연결된 DB의 넘버링 전략을 따라간다.
    private int number; // 유저번호(시퀀스 or auto increment)

    @Column(nullable = false, length = 30)
    private String id; // 유저 아이디

    @Column(nullable = false, length = 100) // 비밀번호 암호화를 위해 길이를 넉넉하게 한다.
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @ColumnDefault("'user'")
    private String role; // Enum을 쓰는게 좋다.(도메인을 지정할 수 있다.)

    @CreationTimestamp // 시간이 자동으로 입력됨.
    private Timestamp signupDate;

    @Column(nullable = true)
    private Timestamp withdrawalDate;


}
