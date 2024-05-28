package br.com.joshua.productchallengeservice.entity.product.port;

public interface DeleteProductPort<IN> {

	public void execute(IN in);

}
