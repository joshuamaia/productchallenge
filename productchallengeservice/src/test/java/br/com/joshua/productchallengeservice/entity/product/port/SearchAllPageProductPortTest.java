package br.com.joshua.productchallengeservice.entity.product.port;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.com.joshua.productchallengeservice.entity.product.adapter.SearchAllPageProductAdapter;
import br.com.joshua.productchallengeservice.entity.product.dto.ProductResponseDTO;
import br.com.joshua.productchallengeservice.entity.product.model.ProductModel;
import br.com.joshua.productchallengeservice.entity.product.repository.ProductModelRepository;

@ExtendWith(MockitoExtension.class)
public class SearchAllPageProductPortTest {

	@Mock
	private ProductModelRepository repository;

	@Mock
	private ModelMapper mapper;

	private SearchAllPageProductPort<Integer, Integer, String, ProductResponseDTO> allPageProductPort;

	@BeforeEach
	public void init() {
		allPageProductPort = new SearchAllPageProductAdapter(repository, mapper);
	}

	@Test
	void whenWordSearchIsNull_thenFetchAllProducts() {
		ProductModel model = new ProductModel();
		model.setId(1L);
		model.setName("testName");
		model.setDescription("testDescription");
		ProductResponseDTO productResponseDTO = new ProductResponseDTO();
		productResponseDTO.setId(1L);
		productResponseDTO.setName("testName");
		productResponseDTO.setDescription("testDescription");
		List<ProductModel> models = Arrays.asList(model);
		Page<ProductModel> page = new PageImpl<>(models);

		when(repository.findAll(any(PageRequest.class))).thenReturn(page);
		when(mapper.map(any(ProductModel.class), eq(ProductResponseDTO.class))).thenReturn(productResponseDTO);

		Page<ProductResponseDTO> result = allPageProductPort.execute(0, 10, null);

		assertNotNull(result);
		assertEquals(1, result.getContent().size());
		assertEquals("testName", result.getContent().get(0).getName());
		assertEquals("testDescription", result.getContent().get(0).getDescription());
		verify(repository).findAll(any(PageRequest.class));
		verify(mapper, times(models.size())).map(any(ProductModel.class), eq(ProductResponseDTO.class));
	}

	@Test
	void whenWordSearchIsNotNull_thenFetchFilteredProducts() {
		String searchWord = "test";
		ProductModel model = new ProductModel();
		model.setId(1L);
		model.setName("testName");
		ProductResponseDTO productResponseDTO = new ProductResponseDTO();
		productResponseDTO.setId(1L);
		productResponseDTO.setName("testName");
		productResponseDTO.setDescription("testDescription");
		List<ProductModel> models = Arrays.asList(model);
		Page<ProductModel> page = new PageImpl<>(models);

		when(repository.searchAllPage(eq(searchWord.toLowerCase()), any(PageRequest.class))).thenReturn(page);
		when(mapper.map(any(ProductModel.class), eq(ProductResponseDTO.class))).thenReturn(productResponseDTO);

		Page<ProductResponseDTO> result = allPageProductPort.execute(0, 10, searchWord);

		assertNotNull(result);
		assertEquals(1, result.getContent().size());
		assertEquals("testName", result.getContent().get(0).getName());
		assertEquals("testDescription", result.getContent().get(0).getDescription());
		verify(repository).searchAllPage(eq(searchWord.toLowerCase()), any(PageRequest.class));
		verify(mapper, times(models.size())).map(any(ProductModel.class), eq(ProductResponseDTO.class));
	}
}
