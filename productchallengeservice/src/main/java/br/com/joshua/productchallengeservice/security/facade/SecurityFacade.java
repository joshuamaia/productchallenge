package br.com.joshua.productchallengeservice.security.facade;

import br.com.joshua.productchallengeservice.entity.user.dto.UserRequestDTO;

public interface SecurityFacade {

	String login(UserRequestDTO userRequestDTO);

}
