package com.jikang.vestige.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

//@DynamicInsert // insert할 때 null인 필드를 제외한다.
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

    //@ColumnDefault("'user'")
    //private String role; // Enum을 쓰는게 좋다.(도메인을 지정할 수 있다.)
    @Enumerated(EnumType.STRING)//DB는 RoleType이 없으므로 @Enumerated로 string임을 알려준다.
    private RoleType role;

    @CreationTimestamp // 시간이 자동으로 입력됨.
    private Timestamp signupDate;

    @Column(nullable = true)
    private Timestamp withdrawalDate;

    public User() {
    }

    public User(int number, String id, String password, String email, RoleType role, Timestamp signupDate, Timestamp withdrawalDate) {
        this.number = number;
        this.id = id;
        this.password = password;
        this.email = email;
        this.role = role;
        this.signupDate = signupDate;
        this.withdrawalDate = withdrawalDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public Timestamp getSignupDate() {
        return signupDate;
    }

    public void setSignupDate(Timestamp signupDate) {
        this.signupDate = signupDate;
    }

    public Timestamp getWithdrawalDate() {
        return withdrawalDate;
    }

    public void setWithdrawalDate(Timestamp withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }
}
