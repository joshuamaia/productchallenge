package br.com.joshua.productchallengeservice.security.adapter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import br.com.joshua.productchallengeservice.entity.user.dto.UserRequestDTO;
import br.com.joshua.productchallengeservice.entity.user.mapper.UserMapper;
import br.com.joshua.productchallengeservice.entity.user.model.UserModel;
import br.com.joshua.productchallengeservice.security.port.GenerateTokenPort;

@Component
public class GenerateTokenAdapter implements GenerateTokenPort<UserRequestDTO, String> {

    private final UserMapper userMapper;

    @Value("${api.security.token.secret}")
    private String secret;

    public GenerateTokenAdapter(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public String execute(UserRequestDTO userRequestDTO) {
        UserModel userModel = this.userMapper.userRequestToUserModel(userRequestDTO);

        try{
           Algorithm algorithm = Algorithm.HMAC256(this.secret);
           return JWT.create()
                   .withIssuer("heath-clinic-api")
                   .withSubject(userModel.getUsername())
                   .withExpiresAt(this.generateExpirationDate())
                   .sign(algorithm);
        } catch (JWTCreationException jwtCreationException){
            //TODO - Class error with details
            throw new RuntimeException("Error with jwt creation {} " + jwtCreationException.getMessage());
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-03:00"));
    }
}
