package br.com.joshua.productchallengeservice.entity.product.adapter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.joshua.productchallengeservice.entity.product.dto.ProductRequestDTO;
import br.com.joshua.productchallengeservice.entity.product.dto.ProductResponseDTO;
import br.com.joshua.productchallengeservice.entity.product.model.ProductModel;
import br.com.joshua.productchallengeservice.entity.product.port.SaveProductPort;
import br.com.joshua.productchallengeservice.entity.product.repository.ProductModelRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SaveProductAdapter implements SaveProductPort<ProductRequestDTO, ProductResponseDTO> {

	private final ProductModelRepository repository;

	private final ModelMapper mapper;

	@Override
	public ProductResponseDTO execute(ProductRequestDTO in) {
		ProductModel model = mapper.map(in, ProductModel.class);
		model = repository.save(model);
		return mapper.map(model, ProductResponseDTO.class);
	}

}
