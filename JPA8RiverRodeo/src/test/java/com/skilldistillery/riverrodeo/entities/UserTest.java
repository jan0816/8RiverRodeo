package com.skilldistillery.riverrodeo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("8RiverRodeoPU");
	}
	

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}
	
//	+----+------------+-----------+---------+
//	| id | first_name | last_name | team_id |
//	+----+------------+-----------+---------+
//	|  1 | Seth       | Schneider |       1 |
//	|  2 | Jan        | Ellsworth |       2 |
//	|  3 | Bob        | Dobbs     |       1 |
//	|  4 | Jane       | Doe       |       2 |
//	+----+------------+-----------+---------+
	
	@Test
	void test() {
		assertNotNull(user);
		assertEquals("Seth", user.getFirstName());
		assertEquals("Schneider", user.getLastName());
	}
	
	@Test
	void test_relationship_mapping_to_team() {
		assertNotNull(user.getTeam());
		assertEquals("Flyman", user.getTeam().getName());
	}
	
	@Test
	void test_relationship_mapping_to_fish() {
		assertNotNull(user.getFishes());
		assertTrue(user.getFishes().size() > 0);
		assertEquals(43.5, user.getFishes().get(0).getSizeInCm());
	}
}
