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

import br.com.joshua.productchallengeservice.entity.product.adapter.UpdateProductAdapter;
import br.com.joshua.productchallengeservice.entity.product.dto.ProductRequestDTO;
import br.com.joshua.productchallengeservice.entity.product.dto.ProductResponseDTO;
import br.com.joshua.productchallengeservice.entity.product.model.ProductModel;
import br.com.joshua.productchallengeservice.entity.product.repository.ProductModelRepository;

@ExtendWith(MockitoExtension.class)
public class UpdateProductPortTest {

    @Mock
    private ProductModelRepository repository;

    @Mock
    private ModelMapper modelMapper;

    private UpdateProductPort<ProductRequestDTO, ProductResponseDTO> updateProductPort;
    
    @BeforeEach
    public void init() {
    	updateProductPort = new UpdateProductAdapter(repository, modelMapper);
	}

    @Test
    void whenExecuteIsCalled_thenProductIsUpdatedAndMapped() {
    	ProductRequestDTO requestDTO = new ProductRequestDTO();
		requestDTO.setName("testName");
		requestDTO.setDescription("testDescription");
		ProductModel model = new ProductModel();
		model.setName("testName");
		model.setDescription("testDescription");
		ProductResponseDTO responseDTO = new ProductResponseDTO();
		responseDTO.setName("testName");
		responseDTO.setDescription("testDescription");
        when(modelMapper.map(requestDTO, ProductModel.class)).thenReturn(model);
        when(repository.save(model)).thenReturn(model);
        when(modelMapper.map(model, ProductResponseDTO.class)).thenReturn(responseDTO);

        ProductResponseDTO result = updateProductPort.execute(requestDTO);

        assertEquals(responseDTO, result, "The response DTO should match the expected DTO");
        assertEquals("testName", result.getName());
		assertEquals("testDescription", result.getDescription());
        verify(modelMapper).map(requestDTO, ProductModel.class); 
        verify(repository).save(model); 
        verify(modelMapper).map(model, ProductResponseDTO.class); 
    }
}
