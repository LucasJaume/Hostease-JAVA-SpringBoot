package com.hostease.tallerHostease.config;

import com.hostease.tallerHostease.model.Usuario;
import com.hostease.tallerHostease.service.JwtService;
import com.hostease.tallerHostease.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("ENTRO EN EL FILTRO JWT AUTHENTICATION FILTER");

        // 1. Obtener encabezado HTTP llamado Authorization
        String authorizationHeader = request.getHeader("Authorization"); // Bearer jwt
        if (!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Obtener token JWT desde el encabezado
        String jwt = authorizationHeader.split(" ")[1];

        // 3. Obtener el subject/username desde el token
        // Esta acción a su vez valida el formato del token, firma y fecha de expiración
        String username = jwtService.extractUsername(jwt);
        if (username == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }

        // 4. Establecer autenticación dentro de SecurityContextHolder
        Usuario user = usuarioService.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User Not found. Username: " + username));
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                username, null, user.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        System.out.println("Se acabó de setear el authentication");

        // 5. Ejecutar el registro de filtro
        filterChain.doFilter(request, response);
    }
}

