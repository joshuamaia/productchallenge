package br.com.joshua.productchallengeservice.security.port;

public interface LoginPort<IN ,OUT> {
    OUT execute(IN in);
}
