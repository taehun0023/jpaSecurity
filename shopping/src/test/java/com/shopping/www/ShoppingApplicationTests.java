package com.shopping.www;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class ShoppingApplicationTests {

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private SqlSessionFactory sessionFactory;
	
	
	@Test
	void contextLoads() {
	}
	
	// DatabaseConfig에서 등록한 sqlSessionFactiory Bean 테스트
	@Test
	public void testByApplicationContext() {
		try {
			// getBean("메서드 명 혹은 @Bean(name = "sqlSessionTest")")
			System.out.println(context.getBean("sqlSessionFactory"));
		} catch (Exception e) {
			e.printStackTrace();		}
	}
	
	
	// 2번째 방법
	@Test
	public void testBySqlSeesionFactory() {
		try {
			System.out.println(sessionFactory.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
