package br.com.projetoweb.projeto.Security;

import java.util.Date;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtil {

    private static final long EXPIRATION = 60*60*1000;
    private static final String EMISSOR = "Projeto_web_api";
    private static final String SECRET_KEY = "Wv4Ivz2R149!&$OqKZu5!64@vDu@Ihbg";
    private static final String PREFIX = "Bearer";

    public static String createToken(String subject) {

        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION);
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretKeyBytes = SECRET_KEY.getBytes();

        String token = Jwts.builder()
                .setSubject(subject)
                .setIssuer(EMISSOR)
                .setExpiration(expirationDate)
                .signWith(signatureAlgorithm,secretKeyBytes)
                .compact();

        return PREFIX+token;
    }

    public static boolean isExpirationValid(Date expiration){
       return expiration.after(new Date(System.currentTimeMillis()));
    }

    public static boolean isEmissorValid(String emissor){
        return emissor.equals(EMISSOR);
     }

    //  public static boolean isTokenValid(String token) {
    //     try {
    //         Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token.replace(PREFIX, ""));
    //         return true;
    //     } catch (JwtException e) {
    //         return false;
    //     }
    }
    
    
    
    
    

}


