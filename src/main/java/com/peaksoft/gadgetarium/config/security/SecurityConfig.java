package com.peaksoft.gadgetarium.config.security;

import com.peaksoft.gadgetarium.config.security.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }


    @Bean
    public AuthenticationManager daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return new ProviderManager(provider);
    }


//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(bCryptPasswordEncoder());
//        provider.setUserDetailsService(userDetailsService());
//        return provider;
//    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> {
                    authorize
                            .requestMatchers("/api/users/sign-up", "/api/users/sign-in").permitAll()
                            .requestMatchers("/swagger-ui/**",
                                    "/swagger-resources/*",
                                    "/v3/api-docs/**").permitAll()
                            .requestMatchers("/api/users/get-all").hasAuthority("ADMIN")
                            .requestMatchers("/api/users/delete/{id}").hasAuthority("ADMIN")
                            .requestMatchers("/api/users/find-by/{id}").hasAuthority("ADMIN")
                            .requestMatchers("/api/users/update/{id}").hasAnyAuthority("ADMIN","USER")
                            .requestMatchers("/api/users/profile").hasAnyAuthority("USER","ADMIN")

                            .requestMatchers("/api/products/save-product/add").hasAuthority("ADMIN")
                            .requestMatchers("/api/products/setDescription/{id}").hasAnyAuthority("ADMIN", "USER")
                            .requestMatchers("/api/products/setPriceAndQuantity/{id}").hasAuthority("ADMIN")
                            .requestMatchers("/api/products/search-product/{id}").hasAnyAuthority("ADMIN", "USER")
                            .requestMatchers("/api/products/compare-oroduct/{id}").hasAnyAuthority("ADMIN", "USER")
                            .requestMatchers("/api/products/search-product-by-filter").hasAnyAuthority("ADMIN", "USER")
                            .requestMatchers("/api/products/delete-products").hasAnyAuthority("ADMIN", "USER")
                            .requestMatchers("/api/products/get-all-products-by-category").hasAnyAuthority("ADMIN", "USER")

                            .requestMatchers("/api/brands/create").hasAuthority("ADMIN")
                            .requestMatchers("/api/brands/get-all-brand").hasAnyAuthority("ADMIN","USER")
                            .requestMatchers("/api/brands/{id}").hasAnyAuthority("ADMIN","USER")
                            .requestMatchers("/api/brands/delete/{id}").hasAuthority("ADMIN")
                            .requestMatchers("/api/brands/update/{id}").hasAuthority("ADMIN")

                            .requestMatchers("/api/category/add-categories").hasAnyRole("ADMIN")
                            .requestMatchers("/api/category/find-by/{id}").hasAnyRole("ADMIN")
                            .requestMatchers("/api/category/get-all").hasAnyRole("ADMIN")
                            .requestMatchers("/api/category/update/{id}").hasAnyRole("ADMIN")
                            .requestMatchers("/api/category/delete/{id}").hasAnyRole("ADMIN")


                            .requestMatchers("/api/mailSenders/sign-up").permitAll()
                            .requestMatchers("/api/mailSenders").permitAll()
                            .requestMatchers("/api/mailSenders/{id}").permitAll()
                            .requestMatchers("/api/mailSenders/update/{id}").permitAll()

                            .anyRequest().authenticated();
                })
//                .httpBasic(Customizer.withDefaults())
//                .authenticationProvider(provider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
