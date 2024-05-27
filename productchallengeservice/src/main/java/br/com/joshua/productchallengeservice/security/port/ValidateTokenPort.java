package br.com.joshua.productchallengeservice.security.port;

public interface ValidateTokenPort<IN, OUT> {

    OUT execute(IN in);
}
