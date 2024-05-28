package br.com.joshua.productchallengeservice.entity.product.facade;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.joshua.productchallengeservice.entity.product.dto.ProductRequestDTO;
import br.com.joshua.productchallengeservice.entity.product.dto.ProductResponseDTO;
import br.com.joshua.productchallengeservice.entity.product.port.DeleteProductPort;
import br.com.joshua.productchallengeservice.entity.product.port.FilterProductPort;
import br.com.joshua.productchallengeservice.entity.product.port.FindOneProductPort;
import br.com.joshua.productchallengeservice.entity.product.port.GetAllProductPort;
import br.com.joshua.productchallengeservice.entity.product.port.SaveProductPort;
import br.com.joshua.productchallengeservice.entity.product.port.SearchAllPageProductPort;
import br.com.joshua.productchallengeservice.entity.product.port.UpdateProductPort;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProductCrudFacadeImpl implements ProductCrudFacade {

	private final SaveProductPort<ProductRequestDTO, ProductResponseDTO> saveProductPort;

	private final UpdateProductPort<ProductRequestDTO, ProductResponseDTO> updateProductPort;

	private final GetAllProductPort<ProductResponseDTO> getAllProductPort;

	private final FindOneProductPort<Long, ProductResponseDTO> findOneProductPort;

	private final FilterProductPort<ProductResponseDTO> filterProductPort;

	private final DeleteProductPort<Long> deleteProductPort;

	private final SearchAllPageProductPort<Integer, Integer, String, ProductResponseDTO> searchAllPageProductPort;

	@Override
	public ProductResponseDTO saveProduct(ProductRequestDTO poroductRequestDTO) {
		return saveProductPort.execute(poroductRequestDTO);
	}

	@Override
	public ProductResponseDTO updateProduct(ProductRequestDTO poroductRequestDTO) {
		return updateProductPort.execute(poroductRequestDTO);
	}

	@Override
	public List<ProductResponseDTO> getAllProduct() {
		return getAllProductPort.execute();
	}

	@Override
	public ProductResponseDTO findOneProduct(Long id) {
		return findOneProductPort.execute(id);
	}

	@Override
	public Page<ProductResponseDTO> filter(String name, String description, Integer page, Integer size) {
		return filterProductPort.execute(name, description, page, size);
	}

	@Override
	public void deleteProduct(Long id) {
		deleteProductPort.execute(id);
	}

	@Override
	public Page<ProductResponseDTO> searchAllPage(Integer page, Integer size, String wordSearch) {
		return searchAllPageProductPort.execute(page, size, wordSearch);
	}

}
