package com.example.book_api.service;

import com.example.book_api.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {
    List<Book> getAllBook();

    Book saveNewBook(Book book);

    Optional<Book> getBookByID(UUID id);

    Optional<Book> updateBook(UUID id, Book book);

    Book deleteByIdOrISBN(UUID id, String isbn);

    Book findByIsbn(String isbn);
}
