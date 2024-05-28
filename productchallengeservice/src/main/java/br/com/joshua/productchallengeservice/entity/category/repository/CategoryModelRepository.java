package br.com.joshua.productchallengeservice.entity.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joshua.productchallengeservice.entity.category.model.CategoryModel;

public interface CategoryModelRepository extends JpaRepository<CategoryModel, Long>{
}
