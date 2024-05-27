package br.com.joshua.productchallengeservice.entity.user.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.joshua.productchallengeservice.entity.user.dto.UserRequestDTO;
import br.com.joshua.productchallengeservice.entity.user.dto.UserResponseDTO;
import br.com.joshua.productchallengeservice.entity.user.model.UserModel;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(source = "username", target = "userName")
	UserResponseDTO userModelToUserResponseDTO(UserModel userModel);

	UserModel userRequestToUserModel(UserRequestDTO userRequestDTO);

	@Mapping(source = "username", target = "userName")
	List<UserResponseDTO> userModelListToUserResponseDTOList(List<UserModel> userModelList);

	UserRequestDTO userModelToUserRequestDTO(UserModel userModel);
}
