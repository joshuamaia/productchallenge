package br.com.joshua.productchallengeservice.entity.category.port;

import java.util.List;

public interface GetAllCategoriesPort<OUT> {

	List<OUT> execute();
}
