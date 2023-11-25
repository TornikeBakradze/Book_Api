package com.example.book_api.controller;

import com.example.book_api.exception.AlreadyExistException;
import com.example.book_api.exception.NotFoundException;
import com.example.book_api.model.Book;
import com.example.book_api.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BookController {

    public static final String BOOK_PATH = "/api/book";
    public static final String BOOK_PATH_ID = BOOK_PATH + "/{bookId}";

      private final BookService bookService;

    @PostMapping(value = BOOK_PATH)
    public ResponseEntity saveNewBook(@RequestBody @Validated Book book) {
        if (bookService.findByIsbn(book.getIsbn()) != null) {
            throw new AlreadyExistException();
        }

        bookService.saveNewBook(book);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = BOOK_PATH)
    public List<Book> getBooks() {
        return bookService.getAllBook();
    }

    @GetMapping(value = BOOK_PATH_ID)
    public Book getBookByID(@PathVariable("bookId") UUID id) {
        return bookService.getBookByID(id).orElseThrow(NotFoundException::new);
    }

    @PutMapping(value = BOOK_PATH_ID)
    public ResponseEntity updateById(@PathVariable("bookId") UUID id,@RequestBody @Validated Book book) {
        if (bookService.updateBook(id, book).isEmpty()) {
            throw new NotFoundException();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = BOOK_PATH)
    public ResponseEntity deleteByIdOrIsbn(@RequestParam(name = "id",required = false) UUID id,
                                           @RequestParam(name = "isbn",required = false)String isbn){
        if(bookService.deleteByIdOrISBN(id, isbn)==null){
            throw new NotFoundException();
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
