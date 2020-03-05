package com.psih.bookapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psih.bookapi.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
