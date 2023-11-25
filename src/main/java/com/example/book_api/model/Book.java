package com.example.book_api.model;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class Book {
    private UUID id;

    @NotNull
    @NotBlank(message = "title is mandatory")
    private String title;

    @NotNull
    @NotBlank(message = "author is mandatory")
    private String author;


    @Max(value = 2023, message = "Release year must be lesser than or equal to 2023")
    private int releaseYear;

    private Genre genre;

    @NotNull
    @NotBlank(message = "isbn is mandatory")
    private String isbn;

    private boolean isAcitve;
}