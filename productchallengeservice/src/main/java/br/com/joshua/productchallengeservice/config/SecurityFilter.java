package br.com.joshua.productchallengeservice.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.joshua.productchallengeservice.entity.user.repository.UserModelRepository;
import br.com.joshua.productchallengeservice.security.port.ValidateTokenPort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	private final ValidateTokenPort<String, String> validateTokenPort;

    private final UserModelRepository userModelRepository;

    public SecurityFilter(ValidateTokenPort<String, String> validateTokenPort, UserModelRepository userModelRepository) {
        this.validateTokenPort = validateTokenPort;
        this.userModelRepository = userModelRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenRecovered = this.recoverToken(request);
        if(tokenRecovered != null){
            String userName = this.validateTokenPort.execute(tokenRecovered);
            UserDetails user = this.userModelRepository.findByUserName(userName);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest httpServletRequest){
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "");
        }else {
            return null;
        }
    }
}
