package br.com.joshua.productchallengeservice.entity.category.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.joshua.productchallengeservice.entity.category.dto.CategoryResponseDTO;
import br.com.joshua.productchallengeservice.entity.category.port.GetAllCategoriesPort;
import br.com.joshua.productchallengeservice.entity.category.repository.CategoryModelRepository;

@Component
public class GetAllCategoriesAdapter implements GetAllCategoriesPort<CategoryResponseDTO> {

	private final CategoryModelRepository categoryModelRepository;

	private final ModelMapper mapper;

	public GetAllCategoriesAdapter(CategoryModelRepository categoryModelRepository, ModelMapper mapper) {
		this.categoryModelRepository = categoryModelRepository;
		this.mapper = mapper;
	}

	@Override
	public List<CategoryResponseDTO> execute() {
		return this.categoryModelRepository.findAll().stream()
				.map(category -> mapper.map(category, CategoryResponseDTO.class)).collect(Collectors.toList());
	}

}
