package com.tdc.sheets.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Provedor de tokens JWT para autenticação
 * 
 * @author Victor Gabriel Soares
 * @since 2025-06-29
 */
@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration}")
    private long jwtExpirationMs;

    @Value("${app.jwt.refresh-expiration}")
    private long jwtRefreshExpirationMs;

    @Value("${app.jwt.issuer}")
    private String jwtIssuer;

    @Value("${app.jwt.audience}")
    private String jwtAudience;

    /**
     * Gera um token JWT a partir da autenticação
     */
    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return generateTokenFromUserPrincipal(userPrincipal, jwtExpirationMs);
    }

    /**
     * Gera um refresh token
     */
    public String generateRefreshToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return generateTokenFromUserPrincipal(userPrincipal, jwtRefreshExpirationMs);
    }

    /**
     * Gera token a partir do UserPrincipal
     */
    private String generateTokenFromUserPrincipal(UserPrincipal userPrincipal, long expiration) {
        Date expiryDate = new Date(System.currentTimeMillis() + expiration);

        List<String> authorities = userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .claim("userId", userPrincipal.getId())
                .claim("nomeCompleto", userPrincipal.getNomeCompleto())
                .claim("email", userPrincipal.getEmail())
                .claim("authorities", authorities)
                .issuer(jwtIssuer)
                .audience().add(jwtAudience).and()
                .issuedAt(new Date())
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Gera um token a partir do username (para renovação)
     */
    public String generateTokenFromUsername(String username, Long userId, String nomeCompleto, 
                                          String email, List<String> authorities) {
        Date expiryDate = new Date(System.currentTimeMillis() + jwtExpirationMs);

        return Jwts.builder()
                .subject(username)
                .claim("userId", userId)
                .claim("nomeCompleto", nomeCompleto)
                .claim("email", email)
                .claim("authorities", authorities)
                .issuer(jwtIssuer)
                .audience().add(jwtAudience).and()
                .issuedAt(new Date())
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * Extrai o username do token
     */
    public String getUsernameFromToken(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    /**
     * Extrai o ID do usuário do token
     */
    public Long getUserIdFromToken(String token) {
        Claims claims = getClaims(token);
        return claims.get("userId", Long.class);
    }

    /**
     * Extrai as authorities do token
     */
    @SuppressWarnings("unchecked")
    public List<String> getAuthoritiesFromToken(String token) {
        Claims claims = getClaims(token);
        return (List<String>) claims.get("authorities");
    }

    /**
     * Extrai a data de expiração do token
     */
    public Date getExpirationDateFromToken(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration();
    }

    /**
     * Valida o token JWT
     */
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            logger.error("Token JWT inválido: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Verifica se o token está expirado
     */
    public boolean isTokenExpired(String token) {
        try {
            Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return true;
        }
    }

    /**
     * Verifica se o token pode ser renovado (não expirou há muito tempo)
     */
    public boolean canTokenBeRefreshed(String token) {
        try {
            Date expiration = getExpirationDateFromToken(token);
            // Permite renovação até 7 dias após expiração
            Date maxRefreshDate = new Date(expiration.getTime() + (7 * 24 * 60 * 60 * 1000));
            return maxRefreshDate.after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Obtém os claims do token
     */
    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .requireIssuer(jwtIssuer)
                .requireAudience(jwtAudience)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Obtém a chave de assinatura
     */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
}
