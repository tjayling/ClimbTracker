package com.qa.climbtracker.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
public class RootTest {
	private static Root root;
	
	@BeforeAll
	static void init() {
		root = new Root();
	}

	@Test
	void loginTest() {
		assertThat(root.login()).isEqualTo("/html/login.html");
	}
	@Test
	void indexTest() {
		assertThat(root.index()).isEqualTo("/html/index.html");
	}
	@Test
	void homeTest() {
		assertThat(root.home()).isEqualTo("/html/home.html");
	}
}