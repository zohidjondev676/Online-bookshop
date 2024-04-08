package com.example.bookshop.springConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        return httpSecurity
                .authorizeHttpRequests(
                        registry -> registry
                                .requestMatchers("/", "/user/**", "/sign-in", "/sign-up", "/js/**", "/css/**", "/images/**", "/cart", "/book/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                ).formLogin(loginConfig -> loginConfig
                        .loginPage("/sign-in")
                        .loginProcessingUrl("/sign-in")
                        .failureUrl("/sign-in?error=true")
                        .usernameParameter("your_name")
                        .passwordParameter("your_pass")
                        .defaultSuccessUrl("/?success=true")
                ).logout(
                        logoutConfig -> logoutConfig
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/logout")
                ).rememberMe(
                        rememberMeConfig -> rememberMeConfig
                                .rememberMeCookieName("remember-me")
                                .tokenValiditySeconds(60 * 60 * 24)
                                .rememberMeParameter("rememberMe")
                )
                .build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }


}
