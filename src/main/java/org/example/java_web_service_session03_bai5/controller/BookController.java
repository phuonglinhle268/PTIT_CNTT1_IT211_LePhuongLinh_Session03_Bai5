package org.example.java_web_service_session03_bai5.controller;

import lombok.RequiredArgsConstructor;
import org.example.java_web_service_session03_bai5.model.entity.Book;
import org.example.java_web_service_session03_bai5.service.IBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {
    private final IBookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(
            @RequestParam(required = false) String author
    ) {
        if (author != null) {
            return new ResponseEntity<>(
                    bookService.searchByAuthor(author),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(
                bookService.getAllBook(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {

        Book book = bookService.getBookById(id);

        if (book == null) {
            return new ResponseEntity<>("Không tìm thấy sách", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {

        Book newBook = bookService.addBook(book);

        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(
            @PathVariable Long id,
            @RequestBody Book book
    ) {
        try {
            Book updatedBook = bookService.updateBook(id, book);
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);

        } catch (NoSuchElementException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBookById(id);

            return new ResponseEntity<>("Xóa thành công", HttpStatus.NO_CONTENT);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}


//phân biệt
//PUT
// cập nhập toàn bộ
//client gửi đầy đủ object
// mang tính thay thế hoàn toàn

//PATCH
// cập nhập 1 phần
// client chỉ gửi field cần sửa
// mang tính chỉnh sửa

// trong bài yêu cầu: Cập nhật toàn bộ thông tin sách theo id
//-> dùng PUT