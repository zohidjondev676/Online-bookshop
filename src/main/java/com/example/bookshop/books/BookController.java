package com.example.bookshop.books;

import com.example.bookshop.books.entity.Book;
import com.example.bookshop.cart.CartService;
import com.example.bookshop.user.UserRepository;
import com.example.bookshop.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final CartService cartService;

    @GetMapping()
    public ModelAndView GetAllBooks(Model model) {

        List<Book> books = bookService.getALl();
        model.addAttribute("books", books);
        return new ModelAndView("index", "model", model);


    }

    @GetMapping("/search")
    public String searchByName(@RequestParam("name") String name, Model model) {
        Book result = bookService.findByName(name);
        model.addAttribute("findByName", result);
        return "/search";
    }


    @GetMapping("book/{id}")
    public String getBookById(Model model, @PathVariable UUID id) {
        Optional<Book> book = bookService.findById(id);
        model.addAttribute("bookById", book);
        return "book/bookInfo";
    }


    @PostMapping("books/addToCart/{bookId}")
    public String addToCart(@PathVariable UUID bookId, Principal principal) {
        User user = userRepository.findByName(principal.getName());
        Book book = bookRepository.findById(bookId).orElse(null);
        if (user != null && book != null) {
            cartService.addToCart(user, book);
        }
        return "redirect:/";
    }

}








