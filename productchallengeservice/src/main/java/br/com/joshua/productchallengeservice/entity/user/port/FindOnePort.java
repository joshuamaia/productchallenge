package br.com.joshua.productchallengeservice.entity.user.port;

public interface FindOnePort<IN, OUT> {

    OUT execute(IN in);
}
