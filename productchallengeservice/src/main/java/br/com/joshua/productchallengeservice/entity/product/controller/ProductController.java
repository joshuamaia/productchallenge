package br.com.joshua.productchallengeservice.entity.product.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joshua.productchallengeservice.entity.product.dto.ProductRequestDTO;
import br.com.joshua.productchallengeservice.entity.product.dto.ProductResponseDTO;
import br.com.joshua.productchallengeservice.entity.product.facade.ProductCrudFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

	private final ProductCrudFacade ProductCrudFacade;

	public ProductController(ProductCrudFacade ProductCrudFacade) {
		this.ProductCrudFacade = ProductCrudFacade;
	}

	@Operation(summary = "Create Product")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Product creted with sucessful", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@PostMapping("create")
	public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
		ProductResponseDTO productSaveResponse = this.ProductCrudFacade.save(productRequestDTO);
		return new ResponseEntity<>(productSaveResponse, HttpStatus.CREATED);
	}

	@Operation(summary = "Search Product By id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the Product", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<ProductResponseDTO> findOne(@PathVariable Long id) {
		ProductResponseDTO findOne = this.ProductCrudFacade.findOne(id);
		return new ResponseEntity<>(findOne, HttpStatus.OK);
	}

	@Operation(summary = "Search By filter")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the Product", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductResponseDTOPage.class))) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@PostMapping("get-by-filters")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<Page<ProductResponseDTO>> getByFilters(Pageable pageable,
			@RequestBody ProductRequestDTO productRequestDTO) {
		Page<ProductResponseDTO> getAllByPage = this.ProductCrudFacade.getByFilters(pageable, productRequestDTO);
		return new ResponseEntity<>(getAllByPage, HttpStatus.OK);
	}

	class ProductResponseDTOPage extends PageImpl<ProductResponseDTO> {
		public ProductResponseDTOPage(List<ProductResponseDTO> content, Pageable pageable, long total) {
			super(content, pageable, total);
		}
	}

}
