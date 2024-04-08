package com.example.bookshop.cart;

import com.example.bookshop.books.entity.Book;
import com.example.bookshop.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {

    @Query("""
            SELECT b
            FROM Cart c
            LEFT JOIN Book b ON c.book.id = b.id
            WHERE c.user.id = :id
            """)
    List<Book> getBooks(@Param("id") UUID userId);
}
