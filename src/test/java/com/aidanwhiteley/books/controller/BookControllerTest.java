package com.aidanwhiteley.books.controller;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.aidanwhiteley.books.domain.Book;
import com.aidanwhiteley.books.repository.BookRepositoryTest;
import com.jayway.jsonpath.JsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookControllerTest.class);

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void findByAuthor() {

		HttpHeaders headers = new HttpHeaders();

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = testRestTemplate.exchange("/api/books?author=Bent Keck", HttpMethod.GET,
				entity, String.class);

		assertTrue(HttpStatus.OK == response.getStatusCode());

		List<Book> books = JsonPath.read(response.getBody(), "$");
		assert (books.size() > 0);

		LOGGER.info("Retrieved JSON was: " + response.getBody());
	}

}