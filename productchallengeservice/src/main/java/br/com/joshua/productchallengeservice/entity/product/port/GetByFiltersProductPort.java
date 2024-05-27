package br.com.joshua.productchallengeservice.entity.product.port;

import org.springframework.data.domain.Page;



public interface GetByFiltersProductPort<IN, IN1, OUT>{

    Page<OUT> execute(IN in, IN1 in1);
}
