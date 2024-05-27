package br.com.joshua.productchallengeservice.entity.user.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String userName;
    private String email;
    private List<RoleDTO> roles;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String token;
}
