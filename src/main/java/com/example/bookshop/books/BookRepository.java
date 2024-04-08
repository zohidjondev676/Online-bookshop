package com.example.bookshop.books;

import com.example.bookshop.books.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    Optional<Book> findByName(String name);
    Optional<Book> findById(UUID id);
}
