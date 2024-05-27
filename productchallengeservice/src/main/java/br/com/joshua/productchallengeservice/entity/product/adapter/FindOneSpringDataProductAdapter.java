package br.com.joshua.productchallengeservice.entity.product.adapter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.joshua.productchallengeservice.entity.product.dto.ProductResponseDTO;
import br.com.joshua.productchallengeservice.entity.product.port.FindOneProductPort;
import br.com.joshua.productchallengeservice.entity.product.repository.ProductModelRepository;

@Component
public class FindOneSpringDataProductAdapter implements FindOneProductPort<Long, ProductResponseDTO> {

	private final ModelMapper mapper;

	private final ProductModelRepository productModelRepository;

	public FindOneSpringDataProductAdapter(ModelMapper mapper, ProductModelRepository productModelRepository) {
		this.mapper = mapper;
		this.productModelRepository = productModelRepository;
	}

	@Override
	public ProductResponseDTO execute(Long in) {
		return mapper.map(productModelRepository.findById(in).orElseThrow(), ProductResponseDTO.class);
	}

}
