package br.com.joshua.productchallengeservice.entity.user.port;

public interface SavePort<IN, OUT> {

    OUT execute(IN in);
}
