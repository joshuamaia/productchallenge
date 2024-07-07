package br.com.joshua.productchallengeservice;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.joshua.productchallengeservice.entity.product.port.DeleteProductPortTest;
import br.com.joshua.productchallengeservice.entity.product.port.FilterProductPortTest;
import br.com.joshua.productchallengeservice.entity.product.port.FindOneProductPortTest;

@Suite
@SelectClasses({FilterProductPortTest.class, DeleteProductPortTest.class, FindOneProductPortTest.class})
@SpringBootTest
class ProductchallengeserviceApplicationTests {

	@Test
	void contextLoads() {
	}

}
