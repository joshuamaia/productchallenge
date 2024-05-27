package br.com.joshua.productchallengeservice.entity.user.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.joshua.productchallengeservice.entity.user.dto.UserRequestDTO;
import br.com.joshua.productchallengeservice.entity.user.dto.UserResponseDTO;

public interface UserCrudFacade {

	UserResponseDTO save(UserRequestDTO userRequestDTO);

	UserResponseDTO findOne(Long id);

	Page<UserResponseDTO> getByFilters(Pageable pageable, UserRequestDTO userRequestDTO);

}
