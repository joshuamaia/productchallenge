package br.com.joshua.productchallengeservice.entity.product.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.joshua.productchallengeservice.entity.product.dto.ProductRequestDTO;
import br.com.joshua.productchallengeservice.entity.product.dto.ProductResponseDTO;
import br.com.joshua.productchallengeservice.entity.product.port.FindOneProductPort;
import br.com.joshua.productchallengeservice.entity.product.port.GetByFiltersProductPort;
import br.com.joshua.productchallengeservice.entity.product.port.SaveProductPort;

@Component
public class ProductCrudFacadeImpl implements ProductCrudFacade {

	private final SaveProductPort<ProductRequestDTO, ProductResponseDTO> savePort;

	private final GetByFiltersProductPort<Pageable, ProductRequestDTO, ProductResponseDTO> getByFiltersPort;

	private final FindOneProductPort<Long, ProductResponseDTO> findOnePort;

	public ProductCrudFacadeImpl(SaveProductPort<ProductRequestDTO, ProductResponseDTO> savePort,
			GetByFiltersProductPort<Pageable, ProductRequestDTO, ProductResponseDTO> getByFiltersPort,
			FindOneProductPort<Long, ProductResponseDTO> findOnePort) {
		this.savePort = savePort;
		this.getByFiltersPort = getByFiltersPort;
		this.findOnePort = findOnePort;

	}

	@Override
	public ProductResponseDTO save(ProductRequestDTO productRequestDTO) {
		return this.savePort.execute(productRequestDTO);
	}

	@Override
	public Page<ProductResponseDTO> getByFilters(Pageable pageable, ProductRequestDTO productRequestDTO) {
		return this.getByFiltersPort.execute(pageable, productRequestDTO);
	}

	@Override
	public ProductResponseDTO findOne(Long id) {
		return this.findOnePort.execute(id);
	}

}
