package br.com.joshua.productchallengeservice.entity.user.adapter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.joshua.productchallengeservice.entity.user.dto.UserRequestDTO;
import br.com.joshua.productchallengeservice.entity.user.dto.UserResponseDTO;
import br.com.joshua.productchallengeservice.entity.user.mapper.UserMapper;
import br.com.joshua.productchallengeservice.entity.user.model.UserModel;
import br.com.joshua.productchallengeservice.entity.user.port.GetByFiltersPort;
import br.com.joshua.productchallengeservice.entity.user.repository.UserModelRepository;
import br.com.joshua.productchallengeservice.entity.user.repository.UserModelSpecification;

@Component
public class GetByFiltersAdapter implements GetByFiltersPort<Pageable, UserRequestDTO, UserResponseDTO> {

    private final UserMapper userMapper;

    private final UserModelRepository userModelRepository;

    public GetByFiltersAdapter(UserMapper userMapper, UserModelRepository userModelRepository) {
        this.userMapper = userMapper;
        this.userModelRepository = userModelRepository;
    }

    @Override
    public Page<UserResponseDTO> execute(Pageable pageable, UserRequestDTO userRequestDTO) {
        UserModel userModelMap = this.userMapper.userRequestToUserModel(userRequestDTO);

        return this.userModelRepository.findAll(UserModelSpecification.getByFilters(userModelMap), pageable)
                .map(this.userMapper::userModelToUserResponseDTO);
    }
}
