package com.example.springdatajpa.repository;

import com.example.springdatajpa.domain.User;

// org.springframework.data.jpa : Spring Data JPA와 관련된 패키지.
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Spring Data JPA Repository를 완성. <- 앞에서 JDBC를 이용해서 만든 DAO와 거의 유사한 것이다.
// 보통 인터페이스를 선언하면? 인터페이스를 구현하는 클래스를 작성해야지.라고 생각한다.
// Spring Data JPA는 마법을 부린다 -> UserRepository를 구현하는 Bean을 자동으로 만들어준다.
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByName(String name); // query method - Spring Data JPA가 제공
    // 동명이인이 있다면 List<User> findByName(String name); 이렇게 만들어야하고 동명이인이 없다는 가정하에 위처럼 만든 것.
}
