package br.com.joshua.productchallengeservice.entity.product.port;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import br.com.joshua.productchallengeservice.entity.product.adapter.GetAllProductAdapter;
import br.com.joshua.productchallengeservice.entity.product.dto.ProductResponseDTO;
import br.com.joshua.productchallengeservice.entity.product.model.ProductModel;
import br.com.joshua.productchallengeservice.entity.product.repository.ProductModelRepository;

@ExtendWith(MockitoExtension.class)
public class GetAllProductPortTest {

    @Mock
    private ProductModelRepository repository;

    @Mock
    private ModelMapper mapper;

    private GetAllProductPort<ProductResponseDTO> getAllProductPort;
    
    @BeforeEach
	public void init() {
    	getAllProductPort = new GetAllProductAdapter(repository, mapper);
	}

    @Test
    void testExecute() {
        ProductModel model1 = new ProductModel(); 
        model1.setId(1L);
		model1.setName("testName");
		model1.setDescription("testDescription");
        ProductModel model2 = new ProductModel(); 
		model2.setId(2L);
		model2.setName("testName2");
		model2.setDescription("testDescription2");
        List<ProductModel> models = Arrays.asList(model1, model2);

        ProductResponseDTO dto1 = new ProductResponseDTO(); 
		dto1.setId(1L);
		dto1.setName("testName");
		dto1.setDescription("testDescription");
        ProductResponseDTO dto2 = new ProductResponseDTO(); 
		dto2.setId(2L);
		dto2.setName("testName2");
		dto2.setDescription("testDescription2");
        List<ProductResponseDTO> dtos = Arrays.asList(dto1, dto2);

        when(repository.findAll()).thenReturn(models);
        when(mapper.map(model1, ProductResponseDTO.class)).thenReturn(dto1);
        when(mapper.map(model2, ProductResponseDTO.class)).thenReturn(dto2);

        List<ProductResponseDTO> result = getAllProductPort.execute();

        assertEquals(dtos.size(), result.size(), "The size of the returned list should match the expected");
        assertTrue(result.containsAll(dtos), "The returned list should contain all expected DTOs");
        assertEquals( "testName", result.get(0).getName());
        assertEquals( "testDescription", result.get(0).getDescription());
        assertEquals( "testName2", result.get(1).getName());
        assertEquals( "testDescription2", result.get(1).getDescription());
        verify(repository).findAll(); // Verify that findAll was called
        verify(mapper, times(models.size())).map(any(ProductModel.class), eq(ProductResponseDTO.class)); // Verify that each product model is mapped to DTO
    }
}
