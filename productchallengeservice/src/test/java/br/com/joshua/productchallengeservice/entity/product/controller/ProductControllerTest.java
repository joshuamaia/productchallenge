package br.com.joshua.productchallengeservice.entity.product.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.joshua.productchallengeservice.entity.product.dto.ProductRequestDTO;
import br.com.joshua.productchallengeservice.entity.product.dto.ProductResponseDTO;
import br.com.joshua.productchallengeservice.entity.product.facade.ProductCrudFacade;
import br.com.joshua.productchallengeservice.entity.user.repository.UserModelRepository;
import br.com.joshua.productchallengeservice.security.port.ValidateTokenPort;

@WebMvcTest(controllers = ProductController.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "CLIENT" })
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductCrudFacade productCrudFacade;

	@MockBean
	private ValidateTokenPort<String, String> validateTokenPort;

	@MockBean
	private UserModelRepository userModelRepository;

	private static final RequestPostProcessor csrf = SecurityMockMvcRequestPostProcessors.csrf();

	@Test
	public void getAllProductsTest() throws Exception {
		given(productCrudFacade.getAllProduct()).willReturn(List.of(new ProductResponseDTO()));

		mockMvc.perform(get("/api/v1/products/list").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)));
	}

	@Test
	public void createProductTest() throws Exception {
		ProductRequestDTO request = new ProductRequestDTO();
		ProductResponseDTO response = new ProductResponseDTO();

		given(productCrudFacade.saveProduct(any(ProductRequestDTO.class))).willReturn(response);

		mockMvc.perform(post("/api/v1/products").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(request)).with(csrf)).andExpect(status().isCreated());
	}

	@Test
	public void updateProductTest() throws Exception {
		ProductRequestDTO request = new ProductRequestDTO();
		ProductResponseDTO response = new ProductResponseDTO();

		given(productCrudFacade.updateProduct(any(ProductRequestDTO.class))).willReturn(response);

		mockMvc.perform(put("/api/v1/products").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(request)).with(csrf)).andExpect(status().isOk());
	}

	@Test
	public void deleteProductByIdTest() throws Exception {
		doNothing().when(productCrudFacade).deleteProduct(anyLong());

		mockMvc.perform(delete("/api/v1/products/{id}", 1L).contentType(MediaType.APPLICATION_JSON).with(csrf))
				.andExpect(status().isNoContent());
	}
}
