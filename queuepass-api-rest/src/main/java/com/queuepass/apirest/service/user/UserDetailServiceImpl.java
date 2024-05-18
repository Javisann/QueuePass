package com.queuepass.apirest.service.user;


import com.queuepass.apirest.controller.DTO.AuthCreateUserRequest;
import com.queuepass.apirest.controller.DTO.AuthLoginRequest;
import com.queuepass.apirest.controller.DTO.AuthResponse;
import com.queuepass.apirest.model.UserModel;
import com.queuepass.apirest.model.security.RoleModel;
import com.queuepass.apirest.repository.RoleRepository;
import com.queuepass.apirest.repository.UserRepository;
import com.queuepass.apirest.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel userModel = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado."));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        userModel.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));
        userModel.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));


        return new User(userModel.getUsername(),
                userModel.getPassword(),
                userModel.getIsEnabled(),
                userModel.getAccountNoExpired(),
                userModel.getAccountNoLocked(),
                userModel.getCredentialNoExpired(),
                authorityList);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {

        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);
        AuthResponse authResponse = new AuthResponse(username, "User loged succesfully", (List<GrantedAuthority>) authentication.getAuthorities().stream().toList(), accessToken, true);
        return authResponse;
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }

        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }

    public AuthResponse createUser(AuthCreateUserRequest createRoleRequest) {

        String username = createRoleRequest.username();
        String password = createRoleRequest.password();
        List<String> roleRequest = List.of("USER"); //Por defecto que sea user siempre que un usuario se registre

        Set<RoleModel> roleModelList = roleRepository.findRoleModelsByRoleEnumIn(roleRequest)
                .stream()
                .collect(Collectors.toSet());

        if (roleModelList.isEmpty()) {
            throw new IllegalArgumentException("The roles specified does not exist."); //Comprueba si esta vacio
        }

        UserModel userModel = UserModel.builder() // Creamos el usuario
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles(roleModelList)
                .isEnabled(true)
                .accountNoLocked(true)
                .accountNoExpired(true)
                .credentialNoExpired(true)
                .build();

        UserModel userSaved = userRepository.save(userModel); //Lo guardamos

        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>(); //Creamos arraylist de permisos

        userSaved.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        userSaved.getRoles().stream().flatMap(role -> role.getPermissionList()
                .stream())
                .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));

        Authentication authentication = new UsernamePasswordAuthenticationToken(userSaved, null, authorities);

        String accessToken = jwtUtils.createToken(authentication);

        AuthResponse authResponse = new AuthResponse(username, "User created successfully", (List<GrantedAuthority>) authentication.getAuthorities().stream().toList(), accessToken, true);
        return authResponse;
    }

}
