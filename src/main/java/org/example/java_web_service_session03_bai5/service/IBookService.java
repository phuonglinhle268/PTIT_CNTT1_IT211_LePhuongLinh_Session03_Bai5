package org.example.java_web_service_session03_bai5.service;

import org.example.java_web_service_session03_bai5.model.entity.Book;

import java.util.List;

public interface IBookService {

    List<Book> getAllBook();
    Book getBookById(Long id);
    Book addBook(Book book);
    Book updateBook(Long id, Book book);
    boolean deleteBookById(Long id);
    List<Book> searchByAuthor(String author);
}