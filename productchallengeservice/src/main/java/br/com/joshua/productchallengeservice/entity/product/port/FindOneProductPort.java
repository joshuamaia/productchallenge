package br.com.joshua.productchallengeservice.entity.product.port;

public interface FindOneProductPort<IN, OUT> {

    OUT execute(IN in);
}
