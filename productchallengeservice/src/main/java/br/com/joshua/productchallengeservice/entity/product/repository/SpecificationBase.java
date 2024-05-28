package br.com.joshua.productchallengeservice.entity.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.constraints.NotNull;

public class SpecificationBase<T> implements Specification<T> {

	private static final long serialVersionUID = 1L;

	@Override
	public Predicate toPredicate(Root<T> root, @NotNull CriteriaQuery<?> criteriaQuery,
			CriteriaBuilder criteriaBuilder) {
		return criteriaBuilder.isNotNull(root.get("id"));
	}

	public Specification<T> findLikeByColumn(String column, @Nullable String nome) {
		return Optional.ofNullable(nome)
				.map(s -> prepareLikeSpecification(column, s))
				.orElse(null);
	}

	@NotNull
	protected Specification<T> prepareEqualsSpecification(@NotNull String columnName, @NotNull Object value) {
		return (root, query, builder) -> builder.equal(root.get(columnName), value);
	}

	@NotNull
	protected Specification<T> prepareLikeSpecification(@NotNull String columnName, @NotNull String value) {
		return (root, query, builder) -> builder.like(builder.lower(root.get(columnName)),
				"%" + value.toLowerCase() + "%");
	}
	
	@NotNull
	protected Specification<T> prepareLikeSubSpecification(@NotNull String columnName, @NotNull String campoName,
			@NotNull String value) {
		return (root, query, builder) -> builder.like(builder.lower(root.join(columnName)
				.get(campoName)), "%" + value.toLowerCase() + "%");
	}

}
