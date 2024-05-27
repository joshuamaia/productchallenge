package br.com.joshua.productchallengeservice.entity.product.repository;

import org.springframework.data.jpa.domain.Specification;

import br.com.joshua.productchallengeservice.entity.product.model.ProductModel;

public class ProductModelSpecification {

	public static Specification<ProductModel> getByFilters(ProductModel productModel) {

		Specification<ProductModel> specificationProductName = null;
		Specification<ProductModel> specificationDescription = null;

		if (productModel.getName() != null) {
			specificationProductName = getByProductNameLike(productModel.getName());
		}

		if (productModel.getDescription() != null) {
			specificationDescription = getByDescriptionLike(productModel.getDescription());
		}

		return Specification.where(specificationProductName).and(specificationDescription);
	}

	private static Specification<ProductModel> getByProductNameLike(String productName) {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.like(root.get("name"), "%" + productName.trim() + "%");
		};
	}

	private static Specification<ProductModel> getByDescriptionLike(String description) {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.like(root.get("description"), "%" + description.trim() + "%");
		};
	}

}
