package br.com.joshua.productchallengeservice.entity.product.port;

public interface SaveProductPort<IN, OUT> {

	OUT execute(IN in);

}