package br.com.joshua.productchallengeservice.entity.product.port;

import java.util.List;

public interface GetAllProductPort<OUT> {

	public List<OUT> execute();

}
