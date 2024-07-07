package br.com.joshua.productchallengeservice;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import br.com.joshua.productchallengeservice.entity.product.port.DeleteProductPortTest;
import br.com.joshua.productchallengeservice.entity.product.port.FilterProductPortTest;
import br.com.joshua.productchallengeservice.entity.product.port.FindOneProductPortTest;
import br.com.joshua.productchallengeservice.entity.product.port.GetAllProductPortTest;
import br.com.joshua.productchallengeservice.entity.product.port.SaveProductPortTest;
import br.com.joshua.productchallengeservice.entity.product.port.SearchAllPageProductPortTest;
import br.com.joshua.productchallengeservice.entity.product.port.UpdateProductPortTest;

@Suite
@SelectClasses({ FilterProductPortTest.class, DeleteProductPortTest.class, FindOneProductPortTest.class,
		GetAllProductPortTest.class, SaveProductPortTest.class, SearchAllPageProductPortTest.class,
		UpdateProductPortTest.class })
public class CrudSuitTest {

}
