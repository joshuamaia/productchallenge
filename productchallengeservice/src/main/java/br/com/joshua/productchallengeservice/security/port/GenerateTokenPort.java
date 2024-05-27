package br.com.joshua.productchallengeservice.security.port;

public interface GenerateTokenPort<IN, OUT>{
    OUT execute(IN in);
}
