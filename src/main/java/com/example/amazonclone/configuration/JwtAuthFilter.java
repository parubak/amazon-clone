package com.example.amazonclone.configuration;

import com.example.amazonclone.exceptions.NotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private UserAuthProvider userAuthProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(header != null) {
            String[] elements = header.split(" ");

            if(elements.length == 2 && elements[0].equals("Bearer")) {
                try {
                    SecurityContextHolder.getContext().setAuthentication(
                            userAuthProvider.validateToken(elements[1]));
                } catch (RuntimeException ex) {
                    SecurityContextHolder.clearContext();

                    throw ex;
                } catch (NotFoundException ex) {
                    SecurityContextHolder.clearContext();
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}