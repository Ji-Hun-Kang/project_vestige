package com.jikang.vestige.domain;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content; // 섬머노트 라이브러리 <html> 태그가 섞여서 디자인이됨.

    @ColumnDefault("0")
    private int count; // 조회수

    @ManyToOne // Many = Board, One = User
    @JoinColumn(name="userNumber")
    private User user; // DB는 오브젝트를 저장할 수 없다. 자바는 오브젝트를 저장할 수 있다.

    @CreationTimestamp
    private Timestamp createDate;

    public Board() {
    }

    public Board(int number, String title, String content, int count, User user, Timestamp createDate) {
        this.number = number;
        this.title = title;
        this.content = content;
        this.count = count;
        this.user = user;
        this.createDate = createDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}
