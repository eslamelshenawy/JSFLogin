package com.authentication;

import com.service.UserRepository;
import com.entity.UserEntity;

import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.HashSet;

public class InMemoryIdentityStore implements IdentityStore {

    @Override
    public CredentialValidationResult validate(Credential credential) {

        UsernamePasswordCredential usernamePasswordCredential = (UsernamePasswordCredential) credential;

        for (UserEntity user : new UserRepository().getAllUsers()) {
            if (usernamePasswordCredential.getCaller().equals(user.getUsername())
                    && usernamePasswordCredential.getPasswordAsString().equals(user.getPassword())) {
                HashSet<String> roles = new HashSet<>();
                if(user.getRole().equals("ADMIN")) {
                    roles.add("ADMIN");
                } else{
                    roles.add("USER");
                }
                return new CredentialValidationResult(user.getFirstName(), roles);
            }
        }

        return CredentialValidationResult.NOT_VALIDATED_RESULT;

    }
}
