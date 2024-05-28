package br.com.joshua.productchallengeservice.entity.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import br.com.joshua.productchallengeservice.entity.product.model.ProductModel;

public class ProductSpecification extends SpecificationBase<ProductModel> {

	private static final long serialVersionUID = 1L;

	public Specification<ProductModel> findByName(@Nullable String name) {
		return Optional.ofNullable(name).map(n -> prepareLikeSpecification("name", n)).orElse(null);
	}

	public Specification<ProductModel> findByDescription(@Nullable String description) {
		return Optional.ofNullable(description).map(n -> prepareLikeSpecification("description", n)).orElse(null);
	}

}
