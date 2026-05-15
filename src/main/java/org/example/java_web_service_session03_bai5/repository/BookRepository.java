package org.example.java_web_service_session03_bai5.repository;

import org.example.java_web_service_session03_bai5.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthorContainingIgnoreCase(String author);
}