package br.com.joshua.productchallengeservice.security.facade;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.joshua.productchallengeservice.entity.user.repository.UserModelRepository;

@Component
public class AuthorizationFacadeImpl implements UserDetailsService {

    private final UserModelRepository userModelRepository;

    public AuthorizationFacadeImpl(UserModelRepository userModelRepository) {
        this.userModelRepository = userModelRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userModelRepository.findByUserName(username);
    }
}
