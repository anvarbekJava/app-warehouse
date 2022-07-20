package uz.pdp.appwarehouse.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.pdp.appwarehouse.domain.Role;

import java.util.Date;

@Component
public class JwtProvider {
    private static  long expireTime = 1000*60*60*24;
    private static String secretKey = "hechkimbilmasin";

    public String generateToken(String phoneNumber, Role roles){
        Date expireDate = new Date(System.currentTimeMillis()+expireTime);
        String token = Jwts
                .builder()
                .setSubject(phoneNumber)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .claim("roles", roles.getName())
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return token;
    }

    public String  getUsernameFromToken(String token){
        try{
            String username = Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return username;
        }catch (Exception e){
            return null;
        }
    }
}
