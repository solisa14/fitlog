package com.github.solisa14.fitlogbackend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for handling JWT (JSON Web Token) operations such as
 * generation, validation, and extraction of claims.
 */
@Component
public class JwtUtil {

    @Value("${jwt.secret_key}")
    private String secretKey;

    @Value("${jwt.expiration_time}")
    private long expirationTime;

    /**
     * Extracts the username (subject) from the JWT token.
     *
     * @param token The JWT token.
     * @return The username extracted from the token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts a specific claim from the JWT token using a claims resolver
     * function.
     *
     * @param token          The JWT token.
     * @param claimsResolver A function to resolve the desired claim from the
     *                       token's claims.
     * @param <T>            The type of the claim to be extracted.
     * @return The extracted claim.
     */
    public <T> T extractClaim(String token, java.util.function.Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Generates a JWT token for the given UserDetails.
     *
     * @param userDetails The UserDetails object representing the user.
     * @return The generated JWT token.
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generates a JWT token with extra claims for the given UserDetails.
     *
     * @param extraClaims Additional claims to include in the token.
     * @param userDetails The UserDetails object representing the user.
     * @return The generated JWT token.
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, expirationTime);
    }

    /**
     * Gets the configured expiration time for JWT tokens.
     *
     * @return The expiration time in milliseconds.
     */
    public long getExpirationTime() {
        return expirationTime;
    }

    /**
     * Builds the JWT token with the specified claims, subject, and expiration.
     *
     * @param extraClaims Additional claims to include in the token.
     * @param userDetails The UserDetails object representing the user
     *                    (subject).
     * @param expiration  The expiration time for the token in milliseconds.
     * @return The constructed JWT token string.
     */
    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails,
                              long expiration) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }

    /**
     * Validates the JWT token against the given UserDetails. Checks if the
     * username in the token matches the UserDetails' username and if the token
     * is not expired.
     *
     * @param token       The JWT token to validate.
     * @param userDetails The UserDetails object to validate against.
     * @return True if the token is valid, false otherwise.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Checks if the JWT token is expired.
     *
     * @param token The JWT token.
     * @return True if the token is expired, false otherwise.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration date from the JWT token.
     *
     * @param token The JWT token.
     * @return The expiration date of the token.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts all claims from the JWT token.
     *
     * @param token The JWT token.
     * @return The Claims object containing all claims from the token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token)
                .getBody();
    }

    /**
     * Retrieves the signing key used for JWT generation and validation. The key
     * is derived from the base64 encoded secret key.
     *
     * @return The signing Key.
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
