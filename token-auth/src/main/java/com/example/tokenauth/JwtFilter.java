package com.example.tokenauth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

/**
 * Esta clase funciona como un middleware que intercepta los requests del usuario
 * Verifica que el request cuente con un Bearer token.
 * Si el token no esta o el mismo es invalido, se responde con error 401
 */
public class JwtFilter extends GenericFilterBean {

    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final String authHeader = request.getHeader("authorization");

        /* Si el metodo es OPTIONS entonces simplemente respondemos */
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req, res);
            return;
        }

        /* Si el encabezado del request no cuenta con un Bearer token entonces enviamos un error */
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "No se envio un token de autenticacion");
            return;
        }

        /* Obtenemos el valor del token */
        final String token = authHeader.substring(7);

        try {
            /* Validamos el token */
            final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
            request.setAttribute("claims", claims);
        } catch (final JwtException e) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Token invalido!");
            return;
        }

        chain.doFilter(req, res);
    }
}
