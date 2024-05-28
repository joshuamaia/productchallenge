package br.com.joshua.productchallengeservice.entity.user.controller;

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

import br.com.joshua.productchallengeservice.entity.user.dto.UserRequestDTO;
import br.com.joshua.productchallengeservice.entity.user.dto.UserResponseDTO;
import br.com.joshua.productchallengeservice.entity.user.facade.UserCrudFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserCrudFacade userCrudFacade;

	public UserController(UserCrudFacade userCrudFacade) {
		this.userCrudFacade = userCrudFacade;
	}

	@Operation(summary = "Create User")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "User creted with sucessful", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@PostMapping("/create")
	public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
		UserResponseDTO userSaveResponse = this.userCrudFacade.save(userRequestDTO);
		return new ResponseEntity<>(userSaveResponse, HttpStatus.CREATED);
	}

	@Operation(summary = "Search User By id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the User", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<UserResponseDTO> findOne(@PathVariable Long id) {
		UserResponseDTO findOne = this.userCrudFacade.findOne(id);
		return new ResponseEntity<>(findOne, HttpStatus.OK);
	}

	@Operation(summary = "Search By filter")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the User", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserResponseDTOPage.class))) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@PostMapping("/get-by-filters")
	@PreAuthorize("hasAnyRole('ADMIN','CLIENT')")
	public ResponseEntity<Page<UserResponseDTO>> getByFilters(Pageable pageable,
			@RequestBody UserRequestDTO userRequestDTO) {
		Page<UserResponseDTO> getAllByPage = this.userCrudFacade.getByFilters(pageable, userRequestDTO);
		return new ResponseEntity<>(getAllByPage, HttpStatus.OK);
	}

	class UserResponseDTOPage extends PageImpl<UserResponseDTO> {
		public UserResponseDTOPage(List<UserResponseDTO> content, Pageable pageable, long total) {
			super(content, pageable, total);
		}
	}

}
