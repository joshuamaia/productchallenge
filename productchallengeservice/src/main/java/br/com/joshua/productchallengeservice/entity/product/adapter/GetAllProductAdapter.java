package br.com.joshua.productchallengeservice.entity.product.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.joshua.productchallengeservice.entity.product.dto.ProductResponseDTO;
import br.com.joshua.productchallengeservice.entity.product.port.GetAllProductPort;
import br.com.joshua.productchallengeservice.entity.product.repository.ProductModelRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class GetAllProductAdapter implements GetAllProductPort<ProductResponseDTO> {

	private final ProductModelRepository repository;

	private final ModelMapper mapper;

	@Override
	public List<ProductResponseDTO> execute() {
		List<ProductResponseDTO> list = this.repository.findAll().stream()
				.map(product -> this.mapper.map(product, ProductResponseDTO.class)).collect(Collectors.toList());
		return list;
	}

}
