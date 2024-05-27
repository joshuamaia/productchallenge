package br.com.joshua.productchallengeservice.entity.user.adapter;

import org.springframework.stereotype.Component;

import br.com.joshua.productchallengeservice.entity.user.dto.UserResponseDTO;
import br.com.joshua.productchallengeservice.entity.user.mapper.UserMapper;
import br.com.joshua.productchallengeservice.entity.user.port.FindOnePort;
import br.com.joshua.productchallengeservice.entity.user.repository.UserModelRepository;

@Component
public class FindOneSpringDataAdapter implements FindOnePort<Long, UserResponseDTO> {

	private final UserMapper userMapper;

	private final UserModelRepository userModelRepositoryr;

	public FindOneSpringDataAdapter(UserMapper userMapper, UserModelRepository userModelRepositoryr) {
		this.userMapper = userMapper;
		this.userModelRepositoryr = userModelRepositoryr;
	}

	@Override
	public UserResponseDTO execute(Long in) {
		return userMapper.userModelToUserResponseDTO(userModelRepositoryr.findById(in).orElseThrow());
	}

}
