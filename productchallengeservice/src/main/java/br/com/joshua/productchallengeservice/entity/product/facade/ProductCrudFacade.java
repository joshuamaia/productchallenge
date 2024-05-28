package br.com.joshua.productchallengeservice.entity.product.facade;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;

import br.com.joshua.productchallengeservice.entity.product.dto.ProductRequestDTO;
import br.com.joshua.productchallengeservice.entity.product.dto.ProductResponseDTO;

public interface ProductCrudFacade {

	ProductResponseDTO saveProduct(ProductRequestDTO oroductRequestDTO );
	
	ProductResponseDTO updateProduct(ProductRequestDTO oroductRequestDTO );
	
	List<ProductResponseDTO> getAllProduct();
	
	ProductResponseDTO findOneProduct(Long id);
	
	Page<ProductResponseDTO> filter(
			@Nullable String name,
			@Nullable String description,
			@Nullable Integer page,
			@Nullable Integer size);
	
	void deleteProduct(Long id);
	
	public Page<ProductResponseDTO> searchAllPage(Integer page, Integer size, String wordSearch);

}
