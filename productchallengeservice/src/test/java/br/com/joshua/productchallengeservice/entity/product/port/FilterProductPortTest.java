package br.com.joshua.productchallengeservice.entity.product.port;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import br.com.joshua.productchallengeservice.entity.product.adapter.FilterProductAdapter;
import br.com.joshua.productchallengeservice.entity.product.dto.ProductResponseDTO;
import br.com.joshua.productchallengeservice.entity.product.model.ProductModel;
import br.com.joshua.productchallengeservice.entity.product.repository.ProductModelRepository;

@ExtendWith(MockitoExtension.class)
public class FilterProductPortTest {

    @Mock
    private ProductModelRepository repository;

    @Mock
    private ModelMapper mapper;

    private FilterProductPort<ProductResponseDTO> filterProductPort;
    
    @BeforeEach
    public void init() {
		filterProductPort = new FilterProductAdapter(repository, mapper);
	}

    @SuppressWarnings("unchecked")
	@Test
    void testExecute() {
        ProductModel productModel = new ProductModel(); 
		productModel.setId(1L);
		productModel.setName("testName");
		productModel.setDescription("testDescription");
		
        ProductResponseDTO productResponseDTO = new ProductResponseDTO(); 
		productResponseDTO.setId(1L);
		productResponseDTO.setName("testName");
		productResponseDTO.setDescription("testDescription");
        Page<ProductModel> page = new PageImpl<>(Arrays.asList(productModel));
        
        when(repository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(page);
        when(mapper.map(any(), eq(ProductResponseDTO.class))).thenReturn(productResponseDTO);

        Page<ProductResponseDTO> result = filterProductPort.execute("testName", "testDescription", 0, 10);

        assertEquals(1, result.getContent().size(), "Should have one DTO");
        assertEquals(productResponseDTO, result.getContent().get(0), "DTO should match the expected one");
        assertEquals( 1L, result.getContent().get(0).getId());
        assertEquals("testName", result.getContent().get(0).getName());
		assertEquals("testDescription", result.getContent().get(0).getDescription());
        verify(repository).findAll(any(Specification.class), any(PageRequest.class)); 
        verify(mapper).map(any(), eq(ProductResponseDTO.class)); 
    }
}
