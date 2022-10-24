package com.example.demo.security.jwt;

import com.example.demo.security.UserDetailsServiceImpl;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@AllArgsConstructor
public class JWTTokenFilter extends OncePerRequestFilter {

    private final JWTService             jwtService;
    private final UserDetailsServiceImpl userDetailsImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if(headerAuthorizationEhValido(authorization)) {
            String token = authorization.substring(7);

            try {
                if (jwtService.isValidToken(token) == true) {
                    String loginCliente = jwtService.captureCliente(token);
                    UserDetails login = userDetailsImpl.loadUserByUsername(loginCliente);

                    UsernamePasswordAuthenticationToken obj = new UsernamePasswordAuthenticationToken(login, null, login.getAuthorities());
                    obj.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(obj);
                }
            } catch(NullPointerException e) {}
        }
        filterChain.doFilter(request, response);
    }

    private boolean headerAuthorizationEhValido(String authorization) {
        return authorization != null && authorization.startsWith("Bearer");
    }
}
