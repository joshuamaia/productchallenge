package br.com.joshua.productchallengeservice.entity.product.adapter;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.joshua.productchallengeservice.entity.product.dto.ProductRequestDTO;
import br.com.joshua.productchallengeservice.entity.product.dto.ProductResponseDTO;
import br.com.joshua.productchallengeservice.entity.product.model.ProductModel;
import br.com.joshua.productchallengeservice.entity.product.port.GetByFiltersProductPort;
import br.com.joshua.productchallengeservice.entity.product.repository.ProductModelRepository;
import br.com.joshua.productchallengeservice.entity.product.repository.ProductModelSpecification;

@Component
public class GetByFiltersProductAdapter
		implements GetByFiltersProductPort<Pageable, ProductRequestDTO, ProductResponseDTO> {

	private final ModelMapper mapper;

	private final ProductModelRepository productModelRepository;

	public GetByFiltersProductAdapter(ModelMapper mapper, ProductModelRepository productModelRepository) {
		this.mapper = mapper;
		this.productModelRepository = productModelRepository;
	}

	@Override
	public Page<ProductResponseDTO> execute(Pageable pageable, ProductRequestDTO productRequestDTO) {
		ProductModel ProductModelMap = this.mapper.map(productRequestDTO, ProductModel.class);
		List<ProductModel> productModelList = this.productModelRepository
				.findAll(ProductModelSpecification.getByFilters(ProductModelMap), pageable).getContent();
		List<ProductResponseDTO> productResponseDTOList = productModelList.stream()
				.map(product -> this.mapper.map(product, ProductResponseDTO.class)).toList();
		return convertListToPage(productResponseDTOList, pageable);
	}

	public Page<ProductResponseDTO> convertListToPage(List<ProductResponseDTO> list, Pageable pageable) {
		return new PageImpl<>(list, pageable, list.size());
	}
}
