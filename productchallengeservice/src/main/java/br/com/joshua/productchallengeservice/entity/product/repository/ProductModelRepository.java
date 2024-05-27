package br.com.joshua.productchallengeservice.entity.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.joshua.productchallengeservice.entity.product.model.ProductModel;

public interface ProductModelRepository extends JpaRepository<ProductModel, Long>, JpaSpecificationExecutor<ProductModel> {
}
