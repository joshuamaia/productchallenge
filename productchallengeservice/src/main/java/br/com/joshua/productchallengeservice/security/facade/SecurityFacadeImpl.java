package br.com.joshua.productchallengeservice.security.facade;

import org.springframework.stereotype.Component;

import br.com.joshua.productchallengeservice.entity.user.dto.UserRequestDTO;
import br.com.joshua.productchallengeservice.security.port.LoginPort;

@Component
public class SecurityFacadeImpl implements SecurityFacade {

	private final LoginPort<UserRequestDTO, String> loginPort;

	public SecurityFacadeImpl(LoginPort<UserRequestDTO, String> loginPort) {
		this.loginPort = loginPort;
	}

	@Override
	public String login(UserRequestDTO userRequestDTO) {
		return this.loginPort.execute(userRequestDTO);
	}

}
