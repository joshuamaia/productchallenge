package br.com.joshua.productchallengeservice.security.adapter;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.joshua.productchallengeservice.entity.user.dto.UserRequestDTO;
import br.com.joshua.productchallengeservice.entity.user.model.UserModel;
import br.com.joshua.productchallengeservice.security.port.GenerateTokenPort;
import br.com.joshua.productchallengeservice.security.port.LoginPort;

@Component
public class LoginAdapter implements LoginPort<UserRequestDTO, String> {

	private final ModelMapper mapper;

	private final GenerateTokenPort<UserRequestDTO, String> generateTokenPort;

	private final AuthenticationManager authenticationManager;

	public LoginAdapter(ModelMapper mapper, GenerateTokenPort<UserRequestDTO, String> generateTokenPort,
			AuthenticationManager authenticationManager) {
		this.mapper = mapper;
		this.generateTokenPort = generateTokenPort;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public String execute(UserRequestDTO userRequestDTO) {
		try {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					userRequestDTO.getUserName(), userRequestDTO.getPasswordUser());
			Authentication authentication = this.authenticationManager
					.authenticate(usernamePasswordAuthenticationToken);
			UserModel userModelAutentication = (UserModel) authentication.getPrincipal();
			UserRequestDTO userRequestDTOAuth = this.mapper.map(userModelAutentication, UserRequestDTO.class);
			userRequestDTOAuth.setUserName(userModelAutentication.getUsername());

			return this.generateTokenPort.execute(userRequestDTOAuth);
		} catch (Exception e) {
			throw new RuntimeException("Error in login {} " + e);
		}
	}
}
