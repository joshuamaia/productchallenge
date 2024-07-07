package br.com.joshua.productchallengeservice.entity.product.port;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.joshua.productchallengeservice.entity.product.adapter.DeleteProductAdapter;
import br.com.joshua.productchallengeservice.entity.product.repository.ProductModelRepository;

@ExtendWith(MockitoExtension.class)
public class DeleteProductPortTest {

	@Mock
    private ProductModelRepository repository;

    private DeleteProductPort<Long> deleteProductPort;
    
    @BeforeEach
    public void init() {
    	deleteProductPort = new DeleteProductAdapter(repository);
    }

    @Test
    void testExecute() {
        Long productId = 1L;

        deleteProductPort.execute(productId);

        verify(repository, times(1)).deleteById(productId);
    }

}
