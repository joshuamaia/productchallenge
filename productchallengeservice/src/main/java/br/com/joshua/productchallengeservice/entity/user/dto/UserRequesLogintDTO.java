package br.com.joshua.productchallengeservice.entity.user.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserRequesLogintDTO {

    private String userName;
    private String passwordUser;
}
