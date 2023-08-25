package com.example.springdatajpa.repository;

import com.example.springdatajpa.domain.User;

// org.springframework.data.jpa : Spring Data JPA와 관련된 패키지.
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

// Spring Data JPA Repository를 완성. <- 앞에서 JDBC를 이용해서 만든 DAO와 거의 유사한 것이다.
// 보통 인터페이스를 선언하면? 인터페이스를 구현하는 클래스를 작성해야지.라고 생각한다.
// Spring Data JPA는 마법을 부린다 -> UserRepository를 구현하는 Bean을 자동으로 만들어준다.
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByName(String name); // query method - Spring Data JPA가 제공
    // 동명이인이 있다면 List<User> findByName(String name); 이렇게 만들어야하고 동명이인이 없다는 가정하에 위처럼 만든 것.

    // where name = ? and email = ?
    Optional<User> findByNameAndEmail(String name, String email);

    // where name like ? or email = ?
    List<User> findByNameOrEmail(String name, String email);

    // where user_id between ? and ?
    List<User> findByUserIdBetween(int startUserId, int endUserId);

    // where user_id < ?
    List<User> findByUserIdLessThan(int userId);

    // where user_id <= ?
    List<User> findByUserIdLessThanEqual(int userId);

    // where user_id > ?
    List<User> findByUserIdGreaterThan(int userId);

    // where user_id >= ?
    List<User> findByUserIdGreaterThanEqual(int userId);

    // where regdate > ?
    List<User> findByRegdateAfter(LocalDateTime day);

    // where regdate < ?
    List<User> findByRegdateBefore(LocalDateTime day);

    // where name is null
    List<User> findByNameIsNull();

    // where name is not null
    List<User> findByNameIsNotNull();

    // where name like ?
    List<User> findByNameLike(String name);

    // where name like '입력한값%'
    List<User> findByNameStartingWith(String name);

    // where name like '%입력한값'
    List<User> findByNameEndingWith(String name);

    // where name like '%입력한값%'
    List<User> findByNameContaining(String name);

    // order by name asc
    List<User> findByOrderByNameAsc();

    // order by name desc
    List<User> findByOrderByNameDesc();

    // where regdate > ? order by name desc
    List<User> findByRegdateAfterOrderByNameDesc(LocalDateTime day);

    // where name <>? (단, null은 나오지 않으니 주의해야한다.)
    List<User> findByNameNot(String name);

    // where user_id in( ..... )
    List<User> findByUserIdIn(Collection<Integer> userIds);

    // where user_id not in( ..... )
    List<User> findByUserIdNotIn(Collection<Integer> userIds);

    // where flag = true
    List<User> findByFlagTrue(); // List<User> findByFlagTrue(); = List<User> findByFlag(boolean flag);

    // where flag = false
    List<User> findByFlagFalse();

}
