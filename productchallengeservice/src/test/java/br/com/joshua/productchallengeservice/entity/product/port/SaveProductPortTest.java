package br.com.joshua.productchallengeservice.entity.product.port;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import br.com.joshua.productchallengeservice.entity.product.adapter.SaveProductAdapter;
import br.com.joshua.productchallengeservice.entity.product.dto.ProductRequestDTO;
import br.com.joshua.productchallengeservice.entity.product.dto.ProductResponseDTO;
import br.com.joshua.productchallengeservice.entity.product.model.ProductModel;
import br.com.joshua.productchallengeservice.entity.product.repository.ProductModelRepository;

@ExtendWith(MockitoExtension.class)
public class SaveProductPortTest {

	@Mock
	private ProductModelRepository repository;

	@Mock
	private ModelMapper mapper;

	private SaveProductPort<ProductRequestDTO, ProductResponseDTO> saveProductPort;

	@BeforeEach
	public void init() {
		saveProductPort = new SaveProductAdapter(repository, mapper);
	}

	@Test
	void whenExecuteIsCalled_thenProductIsSavedAndMapped() {
		ProductRequestDTO requestDTO = new ProductRequestDTO();
		requestDTO.setName("testName");
		requestDTO.setDescription("testDescription");
		ProductModel model = new ProductModel();
		model.setName("testName");
		model.setDescription("testDescription");
		ProductResponseDTO responseDTO = new ProductResponseDTO();
		responseDTO.setName("testName");
		responseDTO.setDescription("testDescription");

		when(mapper.map(requestDTO, ProductModel.class)).thenReturn(model);
		when(repository.save(model)).thenReturn(model);
		when(mapper.map(model, ProductResponseDTO.class)).thenReturn(responseDTO);

		ProductResponseDTO result = saveProductPort.execute(requestDTO);

		assertEquals(responseDTO, result, "The response DTO should match the expected DTO");
		assertEquals("testName", result.getName());
		assertEquals("testDescription", result.getDescription());
		verify(mapper).map(requestDTO, ProductModel.class);
		verify(repository).save(model);
		verify(mapper).map(model, ProductResponseDTO.class);
	}
}
