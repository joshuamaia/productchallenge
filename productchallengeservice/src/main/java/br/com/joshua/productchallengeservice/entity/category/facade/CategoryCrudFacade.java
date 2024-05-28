package br.com.joshua.productchallengeservice.entity.category.facade;

import java.util.List;

import br.com.joshua.productchallengeservice.entity.category.dto.CategoryResponseDTO;

public interface CategoryCrudFacade {

	List<CategoryResponseDTO> findAll();

}
