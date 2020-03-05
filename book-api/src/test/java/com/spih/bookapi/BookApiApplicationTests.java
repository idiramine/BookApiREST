package com.spih.bookapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.psih.bookapi.BookApiApplication;
import com.psih.bookapi.entity.Book;
import com.psih.bookapi.repository.BookRepository;

@SpringBootTest(classes = BookApiApplication.class)
@RunWith(SpringRunner.class)
//@WebMvcTest(value = BookController.class)
@AutoConfigureMockMvc
class BookApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private BookRepository bookRepository;

	@Test
	void contextLoads() throws Exception {

		final List<Book> malist = new ArrayList<>();
		Book book = new Book();
		book.setAuthor("J.R DOS SANTOS");
		book.setId(1L);
		book.setTitle("la formule de dieu");
		malist.add(book);
		book = new Book();
		book.setAuthor("Dan Brown");
		book.setId(2L);
		book.setTitle("da vinci code");
		malist.add(book);

		Mockito.when(bookRepository.findAll()).thenReturn(malist);

		final MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/book").accept(MediaType.APPLICATION_JSON)).andReturn();

		Mockito.verify(bookRepository).findAll();
		final String myJSONResponse = "[{\"id\":1,\"title\":\"la formule de dieu\",\"author\":\"J.R DOS SANTOS\"},{\"id\":2,\"title\":\"da vinci code\",\"author\":\"Dan Brown\"}]";
		assertEquals(myJSONResponse, mvcResult.getResponse().getContentAsString());

	}

}
