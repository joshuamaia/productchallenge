package br.com.joshua.productchallengeservice.entity.user.adapter;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.joshua.productchallengeservice.entity.user.dto.UserRequestDTO;
import br.com.joshua.productchallengeservice.entity.user.dto.UserResponseDTO;
import br.com.joshua.productchallengeservice.entity.user.mapper.UserMapper;
import br.com.joshua.productchallengeservice.entity.user.model.UserModel;
import br.com.joshua.productchallengeservice.entity.user.port.SavePort;
import br.com.joshua.productchallengeservice.entity.user.repository.UserModelRepository;

@Component
public class SaveSpringDataAdapter implements SavePort<UserRequestDTO, UserResponseDTO> {

    private final UserMapper userMapper;

    private final UserModelRepository userModelRepositoryr;

    public SaveSpringDataAdapter(UserMapper userMapper, UserModelRepository userModelRepositoryr) {
        this.userMapper = userMapper;
        this.userModelRepositoryr = userModelRepositoryr;
    }

    @Override
    @Transactional(readOnly = false)
    public UserResponseDTO execute(UserRequestDTO userRequestDTO) {
        UserModel user = this.userMapper.userRequestToUserModel(userRequestDTO);
        user.setPasswordUser(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setCreateAt(LocalDateTime.now());
        try{
            this.userModelRepositoryr.save(user);
            return this.userMapper.userModelToUserResponseDTO(user);
        }catch(Exception exception){
            throw new RuntimeException("Error trying save a new User {} " + exception.getMessage());
        }
    }
}
