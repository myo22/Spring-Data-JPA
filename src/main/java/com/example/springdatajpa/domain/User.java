package com.example.springdatajpa.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // Database Table과 맵핑하는 객체.
@Table(name = "user2") // Database 테이블 이름이 user2와 User라는 객체가 맵핑.
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id // 이 필드가 Table의 PK.
    @Column(name = "user_id") // userId 필드는 user_id와 관련이 있다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // userId는 자동으로 생성되도록 하는 것이다. 1.2.3.4... = AutoIncrement
    private Integer userId;

    @Column(length = 255)
    private String email;

    @Column(length = 50)
    private String name;

    @Column(length = 500)
    private String password;

    @CreationTimestamp // 현재시간이 저장될 때 자동으로 생성.
    private LocalDateTime regdate;
}
