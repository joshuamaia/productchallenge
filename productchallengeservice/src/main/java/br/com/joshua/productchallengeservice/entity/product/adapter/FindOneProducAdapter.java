package br.com.joshua.productchallengeservice.entity.product.adapter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.joshua.productchallengeservice.entity.product.dto.ProductResponseDTO;
import br.com.joshua.productchallengeservice.entity.product.port.FindOneProductPort;
import br.com.joshua.productchallengeservice.entity.product.repository.ProductModelRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FindOneProducAdapter implements FindOneProductPort<Long, ProductResponseDTO> {

	private final ProductModelRepository repository;

	private final ModelMapper mapper;

	@Override
	public ProductResponseDTO execute(Long in) {
		return mapper.map(repository.findById(in), ProductResponseDTO.class);
	}

}
