package br.com.joshua.productchallengeservice.security.adapter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import br.com.joshua.productchallengeservice.entity.user.dto.UserRequestDTO;
import br.com.joshua.productchallengeservice.entity.user.model.UserModel;
import br.com.joshua.productchallengeservice.security.port.GenerateTokenPort;

@Component
public class GenerateTokenAdapter implements GenerateTokenPort<UserRequestDTO, String> {

	private final ModelMapper mapper;

    @Value("${api.security.token.secret}")
    private String secret;

    public GenerateTokenAdapter(ModelMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public String execute(UserRequestDTO userRequestDTO) {
        UserModel userModel = this.mapper.map(userRequestDTO, UserModel.class);

        try{
           Algorithm algorithm = Algorithm.HMAC256(this.secret);
           return JWT.create()
                   .withIssuer("heath-clinic-api")
                   .withSubject(userModel.getUsername())
                   .withExpiresAt(this.generateExpirationDate())
                   .sign(algorithm);
        } catch (JWTCreationException jwtCreationException){
            throw new RuntimeException("Error with jwt creation {} " + jwtCreationException.getMessage());
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-03:00"));
    }
}
