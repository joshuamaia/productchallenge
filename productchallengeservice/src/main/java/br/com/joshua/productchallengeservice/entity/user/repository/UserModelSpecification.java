package br.com.joshua.productchallengeservice.entity.user.repository;

import org.springframework.data.jpa.domain.Specification;

import br.com.joshua.productchallengeservice.entity.user.model.UserModel;

public class UserModelSpecification {

    public static Specification<UserModel> getByFilters(UserModel userModel){

        Specification<UserModel> specificationId = null;
        Specification<UserModel> specificationUserName = null;
        Specification<UserModel> specificationEmail = null;

        if(userModel.getId() != null){
            specificationId = getById(userModel.getId());
        }

        if(userModel.getUsername() != null){
            specificationUserName = getByUserNameLike(userModel.getUsername());
        }

        if(userModel.getEmail() != null){
            specificationEmail = getByEmailLike(userModel.getEmail());
        }


        return Specification.where(specificationId)
                .and(specificationUserName)
                .and(specificationEmail);
    }

    private static Specification<UserModel> getById(Long id){
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("id"), id);
        };
    }

    private static Specification<UserModel> getByUserNameLike(String userName){
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("userName"), "%" + userName.trim() + "%");
        };
    }

    private static Specification<UserModel> getByEmailLike(String email){
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("email"), "%" + email.trim() + "%");
        };
    }


}
