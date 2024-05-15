package com.example.amazonclone.config;

import com.example.amazonclone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private UserService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().userDetailsService(userDetailsService)
                .authenticationProvider(authProvider())
                .authorizeHttpRequests((requests) -> requests
                                .requestMatchers("/login/**").permitAll()
                                .requestMatchers("/dell/**").permitAll()
                                .requestMatchers("/styles/**").denyAll()
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/basket/**","/order").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
//                        .anyRequest().authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin((form) -> form.
                                loginPage("/login")
                                .loginProcessingUrl("/login-ok").
                                usernameParameter("number").
                                passwordParameter("password")
                                .defaultSuccessUrl("/")
//                        .failureUrl("/eror")
                                .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .exceptionHandling().accessDeniedPage("/access-denied");
        return http.build();
    }


    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                .addResourceHandler("css/**")
                .addResourceLocations("classpath:/static/css/");
        registry
                .addResourceHandler("js/**")
                .addResourceLocations("classpath:/static/js/");
        registry
                .addResourceHandler("fonts/**")
                .addResourceLocations("classpath:/static/fonts/");
        registry
                .addResourceHandler("img/**")
                .addResourceLocations("classpath:/static/img/");
        registry
                .addResourceHandler("scss/**")
                .addResourceLocations("classpath:/static/scss/");


//        registry.addResourceHandler("/img/**")
//                        .addResourceLocations("file:/"+ uploadPath + "/");


        exposeDirectory("dell", registry);
        exposeDirectory(uploadPath, registry);
    }

    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");

        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + uploadPath + "/");
    }


}