package com.example.book_api.service;

import com.example.book_api.exception.NotFoundException;
import com.example.book_api.model.Book;
import com.example.book_api.model.Genre;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    public Map<UUID, Book> bookMap;

    public BookServiceImpl() {
        this.bookMap = new HashMap<>();

        Book book1 = Book.builder()
                .id(UUID.randomUUID())
                .title("The Enigmatic Odyssey")
                .author("Samantha Hart")
                .genre(Genre.HISTORICAL)
                .releaseYear(2022)
                .isbn("978-1-234567-89-0")
                .isAcitve(true)
                .build();

        Book book2 = Book.builder()
                .id(UUID.randomUUID())
                .title("Whispering Shadows")
                .author("James Mitchell")
                .genre(Genre.Horror)
                .releaseYear(2019)
                .isbn("978-2-345678-90-1")
                .isAcitve(true)
                .build();

        Book book3 = Book.builder()
                .id(UUID.randomUUID())
                .title("Midnight Serenade")
                .author("Emily Carter")
                .genre(Genre.FANTASY)
                .releaseYear(2020)
                .isbn("978-3-456789-01-2")
                .isAcitve(true)
                .build();

        bookMap.put(book1.getId(), book1);
        bookMap.put(book2.getId(), book2);
        bookMap.put(book3.getId(), book3);
    }

    @Override
    public List<Book> getAllBook() {
        return new ArrayList<>(bookMap.values());
    }

    @Override
    public Book saveNewBook(Book book) {

        Book saveBook = Book.builder()
                .id(UUID.randomUUID())
                .title(book.getTitle())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .releaseYear(book.getReleaseYear())
                .isbn(book.getIsbn())
                .isAcitve(true)
                .build();

        bookMap.put(saveBook.getId(), saveBook);

        return saveBook;
    }

    @Override
    public Optional<Book> getBookByID(UUID id) {
        return Optional.ofNullable(bookMap.get(id));
    }

    @Override
    public Optional<Book> updateBook(UUID id, Book book) {
        Book existbook = bookMap.get(id);
        if(existbook!=null&&existbook.isAcitve()==true){
            existbook.setTitle(book.getTitle());
            existbook.setAuthor(book.getAuthor());
            existbook.setGenre(book.getGenre());
            existbook.setReleaseYear(book.getReleaseYear());
            existbook.setIsbn(book.getIsbn());
            return Optional.of(existbook);
        }

        return Optional.empty();
    }

    @Override
    public Book deleteByIdOrISBN(UUID id, String isbn) {

        if (bookMap.containsKey(id)) {
            Book book = bookMap.get(id);
            book.setAcitve(false);
            return book;
        }

        if (isbn != null) {
            if (findByIsbn(isbn) != null) {
                return bookMap.remove(findByIsbn(isbn).getId());
            }
        }
        return null;
    }

    @Override
    public Book findByIsbn(String isbn) {
        for (Book book : bookMap.values()) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
}
