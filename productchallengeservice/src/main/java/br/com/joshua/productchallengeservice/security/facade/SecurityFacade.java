package br.com.joshua.productchallengeservice.security.facade;

import br.com.joshua.productchallengeservice.entity.user.dto.UserRequesLogintDTO;

public interface SecurityFacade {

	String login(UserRequesLogintDTO userRequesLogintDTO);

}
