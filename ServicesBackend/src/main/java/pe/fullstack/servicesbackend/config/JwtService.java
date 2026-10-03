package pe.fullstack.servicesbackend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pe.fullstack.servicesbackend.controller.request.AuthRequest;


import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    //public static final String SECRET = "5367566859703373367639792F423F452848284D6251655468576D5A71347437";

    @Value("${jwt.secret}")
    private String SECRET;

    public String generateToken(String username) { // Use email as username
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    public String generateSpecialToken(String username, Authentication authentication, AuthRequest authRequest) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("email", authRequest.getEmail());
        //claims.put("password", authRequest.getClave());
        UserInfoDetails auth = (UserInfoDetails) authentication.getPrincipal();
        claims.put("id", auth.getUsuarioId());
        String roles = "";
        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
            roles += grantedAuthority.getAuthority() + ",";
        }
        claims.put("roles", roles);

        return createToken(claims, username);

    }

    private String createToken(Map<String, Object> claims, String secret) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(secret)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Boolean validateToken(String token) {
        final String username = extractUsername(token);
        return username != null;
    }

    public String refreshToken(String jwt) {
        Claims old = Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return Jwts.builder()
                .setClaims(old)
                .setSubject(old.getSubject())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Collection<? extends GrantedAuthority> extractRoles(String token) {
        Claims claims = extractAllClaims(token);

        String[] roles = claims.get("roles", String.class).split(",");

        List<GrantedAuthority> rolesAuthority = new ArrayList<>();
        for (String role : roles) {
            rolesAuthority.add(new SimpleGrantedAuthority(role));
        }
        return rolesAuthority;
    }

    public UserInfoDetails extractUserDetails(String token) {
        Claims claims = extractAllClaims(token);

        UserInfoDetails userInfo = new UserInfoDetails();

        userInfo.setUsuarioId(claims.get("id", Integer.class));
        userInfo.setEmail(claims.get("email", String.class));
        //userInfo.setClave(claims.get("password", String.class));
        userInfo.setAuthorities(new ArrayList<>(extractRoles(token)));

        return userInfo;
    }
}