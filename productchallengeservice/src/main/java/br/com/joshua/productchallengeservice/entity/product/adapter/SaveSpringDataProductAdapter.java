package br.com.joshua.productchallengeservice.entity.product.adapter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.joshua.productchallengeservice.entity.product.dto.ProductRequestDTO;
import br.com.joshua.productchallengeservice.entity.product.dto.ProductResponseDTO;
import br.com.joshua.productchallengeservice.entity.product.model.ProductModel;
import br.com.joshua.productchallengeservice.entity.product.port.SaveProductPort;
import br.com.joshua.productchallengeservice.entity.product.repository.ProductModelRepository;

@Component
public class SaveSpringDataProductAdapter implements SaveProductPort<ProductRequestDTO, ProductResponseDTO> {

	private final ModelMapper mapper;

	private final ProductModelRepository productModelRepository;

	public SaveSpringDataProductAdapter(ModelMapper mapper, ProductModelRepository productModelRepository) {
		this.mapper = mapper;
		this.productModelRepository = productModelRepository;
	}

	@Override
	@Transactional(readOnly = false)
	public ProductResponseDTO execute(ProductRequestDTO productRequestDTO) {
		ProductModel product = this.mapper.map(productRequestDTO, ProductModel.class);
		try {
			this.productModelRepository.save(product);
			return this.mapper.map(product, ProductResponseDTO.class);
		} catch (Exception exception) {
			throw new RuntimeException("Error trying save a new Product {} " + exception.getMessage());
		}
	}
}
