package br.com.joshua.productchallengeservice.entity.product.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.joshua.productchallengeservice.entity.product.dto.ProductRequestDTO;
import br.com.joshua.productchallengeservice.entity.product.dto.ProductResponseDTO;

public interface ProductCrudFacade {

	ProductResponseDTO save(ProductRequestDTO productRequestDTO);

	ProductResponseDTO findOne(Long id);

	Page<ProductResponseDTO> getByFilters(Pageable pageable, ProductRequestDTO productRequestDTO);

}
