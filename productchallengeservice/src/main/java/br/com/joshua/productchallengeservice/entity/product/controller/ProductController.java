package br.com.joshua.productchallengeservice.entity.product.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/api/v1/products")
public class ProductController {

	private final ProductCrudFacade productCrudFacade;

	public ProductController(ProductCrudFacade productCrudFacade) {
		this.productCrudFacade = productCrudFacade;
	}

	@Operation(summary = "Search all Products")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the List of ProductModel", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductResponseDTO.class))) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	@GetMapping("/list")
	public ResponseEntity<List<ProductResponseDTO>> getAll() {
		List<ProductResponseDTO> products = productCrudFacade.getAllProduct();
		return new ResponseEntity<List<ProductResponseDTO>>(products, HttpStatus.OK);
	}

	@Operation(summary = "Search all Products pagened")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the List of ProductModel", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductPage.class))),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	@GetMapping(value = { "/{page}/{size}", "/{page}/{size}/{wordSearch}" })
	public ResponseEntity<Page<ProductResponseDTO>> getAll(@PathVariable Integer page, @PathVariable Integer size,
			@PathVariable(required = false) String wordSearch) {
		Page<ProductResponseDTO> products = productCrudFacade.searchAllPage(page, size, wordSearch);
		return new ResponseEntity<Page<ProductResponseDTO>>(products, HttpStatus.OK);
	}

	@Operation(summary = "Search all Products pagened")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the List of ProductModel", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductPage.class))),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	@GetMapping("/filter")
	public ResponseEntity<Page<ProductResponseDTO>> filter(@RequestParam(required = false) String name,
			@RequestParam(required = false) String description, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {

		var Products = this.productCrudFacade.filter(name, description, page, size);
		return ResponseEntity.ok().body(Products);
	}

	@Operation(summary = "Create ProductModel")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "ProductModel created with sucessful", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	@PostMapping
	public ResponseEntity<ProductResponseDTO> create(@RequestBody ProductRequestDTO productRequestDTO) {
		ProductResponseDTO productSave = productCrudFacade.saveProduct(productRequestDTO);
		return new ResponseEntity<ProductResponseDTO>(productSave, HttpStatus.CREATED);
	}

	@Operation(summary = "Update ProductModel")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "ProductModel updated with sucessful", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	@PutMapping
	public ResponseEntity<ProductResponseDTO> update(@RequestBody ProductRequestDTO productRequestDTO) {
		ProductResponseDTO productUpdate = productCrudFacade.updateProduct(productRequestDTO);
		return new ResponseEntity<ProductResponseDTO>(productUpdate, HttpStatus.OK);
	}

	@Operation(summary = "Search ProductModel By id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the ProductModel", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDTO> getOneById(@PathVariable Long id) {
		ProductResponseDTO product = productCrudFacade.findOneProduct(id);
		return new ResponseEntity<ProductResponseDTO>(product, HttpStatus.OK);
	}

	@Operation(summary = "Delete ProductModel By id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "No Content", description = "Delete ProductModel", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		productCrudFacade.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}

	class ProductPage extends PageImpl<ProductResponseDTO> {
		public ProductPage(List<ProductResponseDTO> content, Pageable pageable, long total) {
			super(content, pageable, total);
		}
	}

}
