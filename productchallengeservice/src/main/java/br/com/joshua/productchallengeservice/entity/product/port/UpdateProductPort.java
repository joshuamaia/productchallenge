package br.com.joshua.productchallengeservice.entity.product.port;

public interface UpdateProductPort<IN, OUT> {
	
	OUT execute(IN in);

}