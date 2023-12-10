package com.example.proyectodaw2324f.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.formLogin(form -> form.successHandler(succesHandler()).permitAll());
        http
                .logout(logout -> logout.logoutSuccessUrl("/").logoutUrl("/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID"));
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/","/index","/register","/login","/css/**","/Foro","/Foro/*","/post","/comentarPost"
                                , "/img/**","/politicaPrivacidad","/ayuda","/acercaDe","/courses","/tienda","/tienda/*"
                                ,"/profileArea","/js/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login").permitAll()
                ).logout((logout) -> logout.permitAll());
        return http.build();
    }
    public AuthenticationSuccessHandler succesHandler() {
        return (request, response, authentication) -> response.sendRedirect("/");
    }

}
