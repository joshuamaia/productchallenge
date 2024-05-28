package br.com.joshua.productchallengeservice.entity.product.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import br.com.joshua.productchallengeservice.entity.product.dto.ProductResponseDTO;
import br.com.joshua.productchallengeservice.entity.product.model.ProductModel;
import br.com.joshua.productchallengeservice.entity.product.port.FilterProductPort;
import br.com.joshua.productchallengeservice.entity.product.repository.ProductModelRepository;
import br.com.joshua.productchallengeservice.entity.product.repository.ProductSpecification;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FilterProductAdapter implements FilterProductPort<ProductResponseDTO> {

	private final ProductModelRepository producRepository;
	
	@Autowired
	private final ModelMapper mapper;

	@Override
	public Page<ProductResponseDTO> execute(@Nullable String name, @Nullable String email, @Nullable Integer page,
			@Nullable Integer size) {
		var specification = this.prepareSpecification(name, email);
		var listPage = this.producRepository.findAll(specification, this.preparePageable(PageRequest.of(page, size)));
		List<ProductModel> list = listPage.getContent();
		List<ProductResponseDTO> listResponse = list.stream().map(product -> this.mapper.map(product, ProductResponseDTO.class)).collect(Collectors.toList());
		return new PageImpl<ProductResponseDTO>(listResponse, PageRequest.of(page, size), listPage.getTotalElements());
	}

	@NotNull
	private Specification<ProductModel> prepareSpecification(@Nullable String name, @Nullable String description) {
		final var specification = new ProductSpecification();

		return specification.and(specification.findByName(name)).and(specification.findByDescription(description));
	}

	private Pageable preparePageable(@Nullable Pageable pageable) {
		return Optional.ofNullable(pageable).orElse(Pageable.unpaged());
	}

}
