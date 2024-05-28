package br.com.joshua.productchallengeservice.entity.product.adapter;

import org.springframework.stereotype.Component;

import br.com.joshua.productchallengeservice.entity.product.port.DeleteProductPort;
import br.com.joshua.productchallengeservice.entity.product.repository.ProductModelRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DeleteProductAdapter implements DeleteProductPort<Long> {

	private final ProductModelRepository repository;

	@Override
	public void execute(Long in) {
		repository.deleteById(in);
	}

}
