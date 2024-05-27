package br.com.joshua.productchallengeservice.entity.user.adapter;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
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
		UserModel userModelMap = this.mapper.map(userRequestDTO, UserModel.class);

		return this.userModelRepository.findAll(UserModelSpecification.getByFilters(userModelMap), pageable)
				.map(user -> this.mapper.map(userModelMap, UserResponseDTO.class));
	}
}
