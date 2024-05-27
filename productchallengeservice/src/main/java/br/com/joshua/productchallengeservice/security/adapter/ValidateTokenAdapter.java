package br.com.joshua.productchallengeservice.security.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.joshua.productchallengeservice.security.port.ValidateTokenPort;

@Component
public class ValidateTokenAdapter implements ValidateTokenPort<String, String> {

    @Value("${api.security.token.secret}")
    private String secret;

    @Override
    public String execute(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            return JWT.require(algorithm)
                    .withIssuer("heath-clinic-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch(JWTVerificationException jwtVerificationException){
            return "";
        }
    }
}
