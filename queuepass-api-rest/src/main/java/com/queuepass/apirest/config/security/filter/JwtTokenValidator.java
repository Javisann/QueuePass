package com.queuepass.apirest.config.security.filter;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.queuepass.apirest.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

/**
 * Clase que valida un token JWT en cada solicitud.
 */
public class JwtTokenValidator extends OncePerRequestFilter {

    private JwtUtils jwtUtils;

    /**
     * Constructor de la clase JwtTokenValidator.
     * @param jwtUtils Objeto JwtUtils utilizado para validar el token JWT.
     */
    public JwtTokenValidator(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    /**
     * Método que se ejecuta para filtrar cada solicitud.
     * @param request Objeto HttpServletRequest que representa la solicitud.
     * @param response Objeto HttpServletResponse que representa la respuesta.
     * @param filterChain Objeto FilterChain utilizado para continuar con la cadena de filtros.
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        // Obtener el token JWT del encabezado de autorización
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (jwtToken != null) {
            // Eliminar el prefijo "Bearer" del token JWT
            jwtToken = jwtToken.substring(7);

            // Validar el token JWT utilizando la clase JwtUtils
            DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);

            // Extraer el nombre de usuario y las autoridades del token
            String username = jwtUtils.extractUsername(decodedJWT);
            String stringAuthorities = jwtUtils.getSpecificClaim(decodedJWT, "authorities").asString();

            // Convertir las autoridades en una lista de GrantedAuthority (Es un objeto que entiende Spring)
            Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(stringAuthorities);

            // Crear un contexto de seguridad vacío y establecer la autenticación
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
            context.setAuthentication(authenticationToken);
            SecurityContextHolder.setContext(context);
        }

        // Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }
}