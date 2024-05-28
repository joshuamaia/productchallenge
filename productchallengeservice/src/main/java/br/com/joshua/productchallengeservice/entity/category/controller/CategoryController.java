package br.com.joshua.productchallengeservice.entity.category.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joshua.productchallengeservice.entity.category.dto.CategoryResponseDTO;
import br.com.joshua.productchallengeservice.entity.category.facade.CategoryCrudFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

	private final CategoryCrudFacade categoryCrudFacade;

	public CategoryController(CategoryCrudFacade categoryCrudFacade) {
		this.categoryCrudFacade = categoryCrudFacade;
	}

	@Operation(summary = "Search All")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the Category", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CategoryResponseDTO.class))) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/list")
	public ResponseEntity<List<CategoryResponseDTO>> getAll() {
		List<CategoryResponseDTO> getAll = this.categoryCrudFacade.findAll();
		return new ResponseEntity<>(getAll, HttpStatus.OK);
	}

}
