package com.example.bookshop.springConfig;


import com.example.bookshop.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

  @GetMapping("/cart")
  public String login() {
       return "cart";
    }




    @PostMapping("/register")
    public String register(@RequestParam("username") String name,
                           @RequestParam("password") String password) {
        userService.register(name, password);
        return "redirect:/login";
    }


    @GetMapping("/sign-in")
    public String signIn() {
        return "reg/sign-in";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "reg/sign-up";
    }


}
