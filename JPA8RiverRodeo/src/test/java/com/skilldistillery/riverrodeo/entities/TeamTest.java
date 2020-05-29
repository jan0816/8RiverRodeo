package com.skilldistillery.riverrodeo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TeamTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Team team;

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
		team = em.find(Team.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		team = null;
	}
	
//	+----+-------------+----------+--------------+------+------+------------------------------------------------------------------------------+
//	| id | name        | password | phone_number | rank | role | picture_url                                                                  |
//	+----+-------------+----------+--------------+------+------+------------------------------------------------------------------------------+
//	|  1 | Flyman      | password | 1239705555   |    1 | user | https://i.pinimg.com/originals/96/57/fc/9657fcf983d0497f4813b01da29e12c5.jpg |
//	|  2 | Pussy power | password | 3211234567   |    2 | user | https://i.ytimg.com/vi/cVxTZFpYKMg/hqdefault.jpg                             |
//	+----+-------------+----------+--------------+------+------+------------------------------------------------------------------------------+

	@Test
	void test() {
		assertNotNull(team);
		assertEquals("Flyman", team.getName());
		assertEquals("password", team.getPassword());
		assertEquals("1239705555",team.getPhoneNumber());
		assertEquals(1,team.getRank());
		assertEquals("user", team.getRole());
		assertEquals("https://i.pinimg.com/originals/96/57/fc/9657fcf983d0497f4813b01da29e12c5.jpg",team.getPictureUrl());

	}
}
