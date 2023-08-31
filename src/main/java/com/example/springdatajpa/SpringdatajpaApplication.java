package com.example.springdatajpa;

import com.example.springdatajpa.dao.BoardIf;
import com.example.springdatajpa.domain.Board;
import com.example.springdatajpa.domain.Role;
import com.example.springdatajpa.domain.User;
import com.example.springdatajpa.repository.BoardRepository;
import com.example.springdatajpa.repository.RoleRepository;
import com.example.springdatajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory; // javax.persistence : 이렇게 시작되면 전부 JPA API이다
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class SpringdatajpaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringdatajpaApplication.class, args);
	}

//	@Autowired
//	EntityManagerFactory entityManagerFactory;

	@Autowired
	UserRepository userRepository; // UserRepository를 구현하고 있는 Bean을 자동으로 inject(주입)을 해준다.

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	BoardRepository boardRepository;

	// org.springframework.transaction.annotation.Transactional;
	// 메소드가 시작할때 트랜잭션이 실행되고, 메소드가 종료할때 트랜잭션이 commit
	// 중간에 RuntimeException이 발생하고 트랜잭션이 rollback
	@Override
	@Transactional
	public void run(String... args) throws Exception {
//		List<User> users = userRepository.findAll(); // select * from user; 1+N 문제 - 쿼리가 너무 많이 실행됨  ---> select * from user, user_role, role where user.user_id = user_role.user_id and user_role.role_id = role.role_id; 이렇게 바꿀수 있음.
//		for(User user : users){
//			System.out.println(user);
//			for(Role role : user.getRoles()) { // select from user_role, role where user_id = ?
//				System.out.println(role);
//			}
//			System.out.println("---------------------------");
//		}

//		List<Role> roles = roleRepository.findAll();
//		for(Role role : roles){
//			System.out.println(role);
//		}

//		// 게시물이 100건일 경우.
//		// 게시물 100건 가지고 오는 Query 1개
//		// 100 * 2번의 사용자 + 권한 정보 가지고 오는 Query 실행.
//		// 1 + N 문제. 총 201개 Query가 실행되버림.
//		List<Board> boards = boardRepository.findAll(); // select * from board
//		for(Board board : boards){
//			System.out.println(board); // board.toString() -> board의 user정보를 가지고 오기 위해서 select * from user을 실행. 만약 roles도 toString 처리 되어있다면, select * from role_role role 조인한것도 실행
//			System.out.println(board.getUser());
//		}

//		Board board = boardRepository.findById(16).get();
//		System.out.println(board);
//		System.out.println(board.getUser()); // lazy로 새로운 SQL이 실행된다.


		List<Board> boards = boardRepository.getBoards(); // findAll과 똑같다.
		for(Board board : boards){
			System.out.println(board);
			System.out.println(board.getUser());
		}

		Long boardCount = boardRepository.getBoardCount();
		System.out.println(boardCount);

		List<BoardIf> list = boardRepository.getBoardsWithNativeQuery();
		for(BoardIf boardIf : list){
			System.out.println(boardIf.getClass().getName()); // BoardIf를 구현하는 객체가 자동으로 select문으로 결과를 담아준다.
			System.out.println(boardIf.getName()); // 글작성자
			System.out.println(boardIf.getTitle());
			System.out.println(boardIf); // 구현하고있는 proxy는 toString 메소드를 구현하고 있지 않기때문에 의미없는 값이 출력.
		}

//		Role role = roleRepository.findById(2).get();
//		System.out.println(role);
//
//		User user = new User();
//		user.setName("관리자");
//		user.setPassword("1234");
//		user.setEmail("dlalsgud12@naver.com");
//		user.setRegdate(LocalDateTime.now());
//		user.setRoles(Set.of(role));
//
//		// user 테이블에 관리자 정보가 insert
//		userRepository.save(user);
//
//		User user = userRepository.findById(10).get();
//		Board board = new Board();
//		board.setUser(user);
//		board.setRegdate(LocalDateTime.now());
//		board.setTitle("관리자님의 글");
//		board.setContent("내용입니다.");
//		boardRepository.save(board);






//		User user =  userRepository.findByNameAndEmail("민형", "dlalsgud12@naver.com").orElseThrow();
//		System.out.println(user);

//		Optional<User> user =  userRepository.findByNameAndEmail("민형", "dlalsgud12@naver.com");
//		System.out.println(user.get());

//		List<User> users = userRepository.findByNameOrEmail("둘리3","dlalsgud12@naver.com");
//		for(User user : users){
//			System.out.println(user);
//		}

//		List<User> users = userRepository.findByUserIdBetween(1,3);
//		for(User user : users){
//			System.out.println(user);
//		}

//		List<User> users = userRepository.findByUserIdLessThan(6);
//		for(User user : users){
//			System.out.println(user);
//		}
//
//		List<User> users = userRepository.findByUserIdLessThanEqual(6);
//		for(User user : users){
//			System.out.println(user);
//		}

//		List<User> users = userRepository.findByUserIdGreaterThan(6);
//		for(User user : users){
//			System.out.println(user);
//		}

//		List<User> users = userRepository.findByUserIdGreaterThanEqual(6);
//		for(User user : users){
//			System.out.println(user);
//		}

//		List<User> users = userRepository.findByRegdateAfter(LocalDateTime.now().minusDays(2L));
//		for(User user : users){
//			System.out.println(user);
//		}

//		List<User> users = userRepository.findByRegdateBefore(LocalDateTime.now().minusDays(2L));
//		for(User user : users){
//			System.out.println(user);
//		}

//		List<User> users = userRepository.findByNameIsNull();
//		for(User user : users){
//			System.out.println(user);
//		}

//		List<User> users = userRepository.findByNameIsNotNull();
//		for(User user : users){
//			System.out.println(user);
//		}

//		List<User> users = userRepository.findByNameLike("민%");
//		for(User user : users){
//			System.out.println(user);
//		}

//		List<User> users = userRepository.findByNameStartingWith("민");
//		for(User user : users){
//			System.out.println(user);
//		}

//		List<User> users = userRepository.findByNameEndingWith("형");
//		for(User user : users){
//			System.out.println(user);
//		}

//		List<User> users = userRepository.findByNameContaining("리");
//		for(User user : users){
//			System.out.println(user);
//		}

//		List<User> users = userRepository.findByOrderByNameAsc();
//		for(User user : users){
//			System.out.println(user);
//		}

//		List<User> users = userRepository.findByOrderByNameDesc();
//		for(User user : users){
//			System.out.println(user);
//		}

//		List<User> users = userRepository.findByRegdateAfterOrderByNameDesc(LocalDateTime.now().minusDays(2L));
//		for(User user : users){
//			System.out.println(user);
//		}

//		List<User> users = userRepository.findByNameNot("민형");
//		for(User user : users){
//			System.out.println(user);
//		}

//		List<User> users = userRepository.findByNameNot("민형");
//		for(User user : users){
//			System.out.println(user);
//		}

//		List<User> users = userRepository.findByUserIdIn(List.of(2,6));
//		for(User user : users){
//			System.out.println(user);
//		}
//
//		List<User> users = userRepository.findByUserIdNotIn(List.of(2,6));
//		for(User user : users){
//			System.out.println(user);
//		}

//		Long count = userRepository.countBy();
//		System.out.println(count);

//		Long count = userRepository.countByNameLike("둘리3");
//		System.out.println(count);

//		boolean find = userRepository.existsByEmail("dlalsgud12@naver.com");
//		System.out.println(find);

//		int count = userRepository.deleteByName("민형");
//		System.out.println(count);

//		List<User> users = userRepository.findDistinctByName("둘리3");
//		for(User user : users){
//			System.out.println(user);
//		}

//		List<User> users = userRepository.findFirst2By();
//		for(User user : users){
//			System.out.println(user);
//		}

//		List<User> users = userRepository.findTop2By();
//		for(User user : users){
//			System.out.println(user);
//		}

//		Page<User> users = userRepository.findBy(PageRequest.of(1, 2, Sort.by(Sort.Direction.DESC, "regdate")));
//		// users는 페이지 객체이기 때문에 getContent를 통해 0번째 페이지에 리스트를 가져오게 된다.
//		for(User user : users.getContent()){
//			System.out.println(user);
//		}

//		Page<User> users = userRepository.findByName("김치만두", PageRequest.of(1, 2, Sort.by(Sort.Direction.DESC, "regdate")));
//
//		for(User user : users.getContent()){
//			System.out.println(user);
//		}









//		System.out.println(userRepository.getClass().getName());
////		Optional<User> Id = userRepository.findById(2);
//		// orElseThrow를 붙이면 2번에 해당하는 값이 있으면 user로 리턴하고 없으면 예외를 발생시킨다.
//		User user = userRepository.findById(2).orElseThrow(); // Java Optional 문법. null을 다루는 기술
//		System.out.println(user);


//      // insert
//		User user = new User();
//		user.setName("김치만두");
//		user.setEmail("enffl@example.com");
//		user.setPassword("1234");
//		user.setRegdate(LocalDateTime.now());
//
//		// 여기서도 save인터페이스를 구현하고 있는 proxy객체가 EntityManager를 이용해서 실제로 저장해준다. -> JPA코드를 하나도 안적어도 JPA프로그래밍이 된다.
//		userRepository.save(user);

//		// save 하자마자 user의 userId가 궁금
//		User saveUser = userRepository.save(user);
//		System.out.println(saveUser);
//		System.out.println(user);
//		if (user == saveUser){
//			System.out.println("user == saveUser");
//		}else{
//			System.out.println("user != saveUser");
//		}

//		// 동명이인 있으면 에러가 난다. List로 받질 않아서 -> 그땐 List<User>  user = userRepository.findByName("둘리3");
//		User user = userRepository.findByName("민형").orElseThrow();
//		System.out.println(user);

//		// delete
//		userRepository.deleteById(3);

//		// delete2
//		User user = userRepository.findById(5).orElseThrow();
//		userRepository.delete(user);

//		// update
//		User user = userRepository.findById(6).orElseThrow();
//		System.out.println(user);
//		user.setPassword("5678");

//		트랜잭션 시작
//		entityTransaction.begin();
//		User user = entityManager.find(User.class, 15);
//		                    ---------------------------------------> Persistence Context ---------> DBMS
//		 															 스냅샷
//			user.setPassword(암호)
// 		트랜잭션 종료 // update query 실행.
//		entityTransaction.commit();







//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//		EntityTransaction transaction = entityManager.getTransaction();
//		try {
//			transaction.begin();
////			// JPA 관련된 코드가 나온다.
////		    User user = new User();
////			user.setName("민형");
////			user.setEmail("dlalsgud12@naver.com");
////			user.setPassword("1234");
////
////			// 영속성을 갖게 해달라 -> 이 데이터가 DBMS에 저장된다.
////			entityManager.persist(user);
//
//			User user = entityManager.find(User.class, 2);
//			User user2 = entityManager.find(User.class, 2);
//
////			if(user == user2) // 같은 참조냐?
////				System.out.println("같은 참조");
////			else
////				System.out.println("다른 참조");
//
//			user.setPassword("5678");
//
//			System.out.println(user2.getPassword());
//
////			System.out.println(user);
////			entityManager.remove(user);
//
//			transaction.commit();
//		}catch (Exception ex){
//			transaction.rollback(); // 문제가 생기면 롤백
//		}finally {
//			entityManager.close ();
//		}
	}
}
