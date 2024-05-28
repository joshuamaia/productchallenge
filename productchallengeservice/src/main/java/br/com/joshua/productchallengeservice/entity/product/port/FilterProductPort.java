package br.com.joshua.productchallengeservice.entity.product.port;

import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;

public interface FilterProductPort<OUT> {

	public Page<OUT> execute(@Nullable String name, @Nullable String description, @Nullable Integer page,
			@Nullable Integer size);

}
