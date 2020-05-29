package com.skilldistillery.riverrodeo.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RiverTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private River river;

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
		river = em.find(River.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		river = null;
	}
	
	@Test
	void test() {
		assertNotNull(river);
		assertEquals("Colorado", river.getName());


	}
}
