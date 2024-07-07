package br.com.joshua.productchallengeservice.entity.product.port;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import br.com.joshua.productchallengeservice.entity.product.adapter.FindOneProducAdapter;
import br.com.joshua.productchallengeservice.entity.product.dto.ProductResponseDTO;
import br.com.joshua.productchallengeservice.entity.product.model.ProductModel;
import br.com.joshua.productchallengeservice.entity.product.repository.ProductModelRepository;

@ExtendWith(MockitoExtension.class)
public class FindOneProductPortTest {

    @Mock
    private ProductModelRepository repository;

    @Mock
    private ModelMapper mapper;

    private FindOneProductPort<Long, ProductResponseDTO> findOneProductPort;
    
	@BeforeEach
	public void init() {
		findOneProductPort = new FindOneProducAdapter(repository, mapper);
	}

    @Test
    void whenProductExists_thenReturnsDto() {
    	Long productId = 1L;
    	ProductModel productModel = new ProductModel(); 
		productModel.setId(1L);
		productModel.setName("testName");
		productModel.setDescription("testDescription");
		
        ProductResponseDTO productResponseDTO = new ProductResponseDTO(); 
		productResponseDTO.setId(1L);
		productResponseDTO.setName("testName");
		productResponseDTO.setDescription("testDescription");

        when(repository.findById(productId)).thenReturn(Optional.of(productModel));
        when(mapper.map(productModel, ProductResponseDTO.class)).thenReturn(productResponseDTO);

        ProductResponseDTO result = findOneProductPort.execute(productId);

        assertEquals(productResponseDTO, result, "The DTO returned was not the expected one");
        assertEquals("testName", result.getName());
        assertEquals("testDescription", result.getDescription());
    }

    @Test
    void whenProductDoesNotExist_thenReturnsNull() {
        Long productId = 1L;
        when(repository.findById(productId)).thenReturn(Optional.empty());

        ProductResponseDTO result = findOneProductPort.execute(productId);

        assertNull(result, "Expected null when product does not exist");
    }
}

