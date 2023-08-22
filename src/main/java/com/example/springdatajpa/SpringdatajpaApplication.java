package com.example.springdatajpa;

import com.example.springdatajpa.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory; // javax.persistence : 이렇게 시작되면 전부 JPA API이다
import javax.persistence.EntityTransaction;

@SpringBootApplication
public class SpringdatajpaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringdatajpaApplication.class, args);
	}

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Override
	public void run(String... args) throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
//			// JPA 관련된 코드가 나온다.
//		    User user = new User();
//			user.setName("민형");
//			user.setEmail("dlalsgud12@naver.com");
//			user.setPassword("1234");
//
//			// 영속성을 갖게 해달라 -> 이 데이터가 DBMS에 저장된다.
//			entityManager.persist(user);

			User user = entityManager.find(User.class, 2);
			User user2 = entityManager.find(User.class, 2);

//			if(user == user2) // 같은 참조냐?
//				System.out.println("같은 참조");
//			else
//				System.out.println("다른 참조");

			user.setPassword("5678");

			System.out.println(user2.getPassword());

//			System.out.println(user);
//			entityManager.remove(user);

			transaction.commit();
		}catch (Exception ex){
			transaction.rollback(); // 문제가 생기면 롤백
		}finally {
			entityManager.close ();
		}
	}
}
