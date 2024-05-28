package br.com.joshua.productchallengeservice.entity.product.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import br.com.joshua.productchallengeservice.entity.product.dto.ProductResponseDTO;
import br.com.joshua.productchallengeservice.entity.product.model.ProductModel;
import br.com.joshua.productchallengeservice.entity.product.port.SearchAllPageProductPort;
import br.com.joshua.productchallengeservice.entity.product.repository.ProductModelRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SearchAllPageProductAdapter implements SearchAllPageProductPort<Integer, Integer, String, ProductResponseDTO> {

	private final ProductModelRepository repository;

	private final ModelMapper mapper;

	@Override
	public Page<ProductResponseDTO> execute(Integer page, Integer size, String wordSearch) {
		PageRequest pageRequest = PageRequest.of(page, size);
		if (wordSearch == null || wordSearch.trim().isEmpty()) {
			Page<ProductModel> listPage = this.repository.findAll(pageRequest);
			List<ProductModel> list = listPage.getContent();
			List<ProductResponseDTO> listResponse = list.stream().map(product -> this.mapper.map(product, ProductResponseDTO.class)).collect(Collectors.toList());
			return new PageImpl<ProductResponseDTO>(listResponse, PageRequest.of(page, size), listPage.getTotalElements());
		}
		wordSearch = wordSearch.toLowerCase();
		Page<ProductModel> listPage = this.repository.searchAllPage(wordSearch, pageRequest);
		List<ProductModel> list = listPage.getContent();
		List<ProductResponseDTO> listResponse = list.stream().map(product -> this.mapper.map(product, ProductResponseDTO.class)).collect(Collectors.toList());
		return new PageImpl<ProductResponseDTO>(listResponse, pageRequest, listPage.getTotalElements());
	}


}
