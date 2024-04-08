package com.example.bookshop.cart;

import com.example.bookshop.books.entity.Book;
import com.example.bookshop.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;


    @GetMapping()
    public ModelAndView getCartBooks(Model model, Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        List<Book> books = cartService.getBooks(principal.getId());
        model.addAttribute("CartBooks", books);
        return new ModelAndView("cart", "model", model);
    }
}
