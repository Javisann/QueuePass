package com.queuepass.apirest.service.user;


import com.queuepass.apirest.model.UserModel;
import com.queuepass.apirest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel userEntity = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado."));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        userEntity.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));
        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));


        return new User(userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getIsEnabled(),
                userEntity.getAccountNoExpired(),
                userEntity.getAccountNoLocked(),
                userEntity.getCredentialNoExpired(),
                authorityList);
    }
}
