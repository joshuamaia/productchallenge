package br.com.joshua.productchallengeservice.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDTO {
	private String token;
}
