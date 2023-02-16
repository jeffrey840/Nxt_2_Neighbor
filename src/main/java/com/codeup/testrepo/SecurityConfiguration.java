package com.codeup.testrepo;

import com.codeup.testrepo.services.UserDetailsLoader;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;


//which pages are available to who
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    protected UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                /* Login configuration */
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/listings",true) // user's home page, it can be any URL
                .permitAll() // Anyone can go to the login page
                /* Logout configuration */
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
//                .deleteCookies("JSESSIONID")// append a query string value
                /* Pages that can be viewed without having to log in */
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/", "/sign-up","/listings","/js/**","/css/**","/img/**", "/contact-us", "/about-us") // anyone can see the home and the ads pages
                .permitAll()
                /* Pages that require authentication */
                .and()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/listings/seller-profile",
                        "/listings/{id}/seller-profile",// only authenticated users can create ads
                        "/seller-profile/{id}/delete",
                        "/listings/seller-create",
                        "/listings/seller-show",
                        "/listings/neighbor-profile",
                        "/listings/{id}/neighbor-profile",// only authenticated users can create ads
                        "/listings/{id}/delete",
                        "/listings/{id}",
                        "/listings",
                        "/home-logged-in",
                        "/seller-create",
                        "/seller-profile",
                        "/seller-show",
                        "/listing/seller-profile/delete",
                        "/listing/seller-profile/update",
                        "/seller-update",
                        "/listings/seller-profile/update",
                        "/listings/seller-update",
                        "/listings/{id}/seller-update"
                )
                .authenticated();
        return http.build();
    }
}



