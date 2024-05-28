package br.com.joshua.productchallengeservice.entity.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.joshua.productchallengeservice.entity.product.model.ProductModel;

public interface ProductModelRepository extends JpaRepository<ProductModel, Long>, JpaSpecificationExecutor<ProductModel> {
	@Query("FROM ProductModel p WHERE LOWER(p.name) like %:wordSearch% OR LOWER(p.description) like %:wordSearch%")
	Page<ProductModel> searchAllPage(@Param("wordSearch") String wordSearch, Pageable pageable);
}
