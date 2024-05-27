package br.com.joshua.productchallengeservice.entity.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.joshua.productchallengeservice.entity.user.model.UserModel;

public interface UserModelRepository extends JpaRepository<UserModel, Long>, JpaSpecificationExecutor<UserModel> {
    UserDetails findByUserName(String userName);
}
