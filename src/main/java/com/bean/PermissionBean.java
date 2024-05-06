package com.bean;

import com.entity.UserEntity;
import com.service.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

@Named
@RequestScoped
public class PermissionBean {

    @Inject
    UserEntity userEntity;

    @Inject
    UserRepository userRepository;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Collection<UserEntity> getAllUsers(){
        return userRepository.getAllUsers();
    }

    public String changePermission(){
        UserEntity user = userRepository.getUserByUsername(userEntity.getUsername());
        userRepository.updatePermission(userEntity.getRole(), user);
        return "permissionSuccess";
    }

}
