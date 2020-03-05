package com.psih.bookapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psih.bookapi.entity.Book;
import com.psih.bookapi.exception.BookNotFoundException;
import com.psih.bookapi.repository.BookRepository;

@RestController
@RequestMapping("/api")
public class BookController {

	@Autowired
	BookRepository bookRepository;

	@PostMapping("/book")
	public Book createBook(@Valid @RequestBody final Book book) {
		return bookRepository.save(book);
	}

	@DeleteMapping("/book/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable(value = "id") final Long bookId) {
		final Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new BookNotFoundException("Book", "id", bookId));

		bookRepository.delete(book);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/book")
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@GetMapping("/book/{id}")
	public Book getBookById(@PathVariable(value = "id") final Long bookId) {
		return bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book", "id", bookId));
	}

	@PutMapping("/book/{id}")
	public Book updateBook(@PathVariable(value = "id") final Long bookId, @Valid @RequestBody final Book bookDetails) {

		bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book", "id", bookId));

		bookDetails.setId(bookId);
		final Book updateBook = bookRepository.save(bookDetails);
		return updateBook;
	}
}
