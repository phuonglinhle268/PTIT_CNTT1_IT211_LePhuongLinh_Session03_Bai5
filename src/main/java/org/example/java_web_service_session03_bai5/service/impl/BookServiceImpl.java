package org.example.java_web_service_session03_bai5.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.java_web_service_session03_bai5.model.entity.Book;
import org.example.java_web_service_session03_bai5.repository.BookRepository;
import org.example.java_web_service_session03_bai5.service.IBookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {

        Book oldBook = getBookById(id);

        if (oldBook == null) {
            throw new NoSuchElementException("Không tìm thấy sách với id: " + id);
        }

        oldBook.setTitle(book.getTitle());
        oldBook.setAuthor(book.getAuthor());
        oldBook.setYear(book.getYear());
        oldBook.setAvailable(book.isAvailable());

        return bookRepository.save(oldBook);
    }

    @Override
    public boolean deleteBookById(Long id) {

        Book oldBook = getBookById(id);

        if (oldBook == null) {
            throw new NoSuchElementException("Không tìm thấy sách với id: " + id);
        }

        bookRepository.delete(oldBook);

        return true;
    }

    @Override
    public List<Book> searchByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }
}