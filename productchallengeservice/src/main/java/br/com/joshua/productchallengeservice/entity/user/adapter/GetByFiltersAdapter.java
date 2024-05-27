package br.com.joshua.productchallengeservice.entity.user.adapter;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.joshua.productchallengeservice.entity.user.dto.UserRequestDTO;
import br.com.joshua.productchallengeservice.entity.user.dto.UserResponseDTO;
import br.com.joshua.productchallengeservice.entity.user.model.UserModel;
import br.com.joshua.productchallengeservice.entity.user.port.GetByFiltersPort;
import br.com.joshua.productchallengeservice.entity.user.repository.UserModelRepository;
import br.com.joshua.productchallengeservice.entity.user.repository.UserModelSpecification;

@Component
public class GetByFiltersAdapter implements GetByFiltersPort<Pageable, UserRequestDTO, UserResponseDTO> {

	private final ModelMapper mapper;

	private final UserModelRepository userModelRepository;

	public GetByFiltersAdapter(ModelMapper mapper, UserModelRepository userModelRepository) {
		this.mapper = mapper;
		this.userModelRepository = userModelRepository;
	}

	@Override
	public Page<UserResponseDTO> execute(Pageable pageable, UserRequestDTO userRequestDTO) {
		UserModel UserModelMap = this.mapper.map(userRequestDTO, UserModel.class);
		List<UserModel> UserModelList = this.userModelRepository.findAll(UserModelSpecification.getByFilters(UserModelMap), pageable).getContent();
		List<UserResponseDTO> UserResponseDTOList = UserModelList.stream().map(User -> this.mapper.map(User, UserResponseDTO.class)).toList();
		return convertListToPage(UserResponseDTOList, pageable);
	}
	
	private Page<UserResponseDTO> convertListToPage(List<UserResponseDTO> list, Pageable pageable) {
        return new PageImpl<>(list, pageable, list.size());
    }
}
