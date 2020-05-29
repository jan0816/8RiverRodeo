package com.skilldistillery.riverrodeo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FishTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Fish fish;

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
		fish = em.find(Fish.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		fish = null;
	}
	
	@Test
	void test() {
		assertNotNull(fish);
		assertEquals(43.5, fish.getSizeInCm());
		assertEquals(1, fish.getRiverId());
		assertEquals(1, fish.getUserId());
		assertEquals(LocalDate.of( 2019,07,26), fish.getDayCaught());


	}
}
