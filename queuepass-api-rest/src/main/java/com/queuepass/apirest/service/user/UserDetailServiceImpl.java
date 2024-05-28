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

import java.util.*;
import java.util.stream.Collectors;

/**
 * Esta clase implementa la interfaz UserDetailsService y se utiliza para gestionar los detalles del usuario.
 */
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

    /**
     * Carga los detalles del usuario por su nombre de usuario.
     *
     * @param username el nombre de usuario del usuario
     * @return los detalles del usuario
     * @throws UsernameNotFoundException si el usuario no se encuentra
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Buscar el usuario en el repositorio por su nombre de usuario
        UserModel userModel = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado."));

        // Crear una lista de autoridades para el usuario
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        userModel.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));
        userModel.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        // Crear y devolver un objeto User con los detalles del usuario
        return new User(userModel.getUsername(),
                userModel.getPassword(),
                userModel.getIsEnabled(),
                userModel.getAccountNoExpired(),
                userModel.getAccountNoLocked(),
                userModel.getCredentialNoExpired(),
                authorityList);
    }

    /**
     * Inicia sesión de un usuario y devuelve un response.
     *
     * @param authLoginRequest el Request de inicio de sesión del usuario
     * @return el response de autenticación
     */
    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {

        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        String name = findNameByUsername(username);


        // Autenticar al usuario
        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Crear un token de acceso JWT
        String accessToken = jwtUtils.createToken(authentication);

        // Crear y devolver un response de autenticación
        AuthResponse authResponse = new AuthResponse(username,  name,"Usuario inició sesión correctamente", (List<GrantedAuthority>) authentication.getAuthorities().stream().toList(), accessToken, true);
        return authResponse;
    }

    /**
     * Autentica al usuario utilizando el nombre de usuario y la contraseña proporcionados.
     *
     * @param username el nombre de usuario
     * @param password la contraseña
     * @return la autenticación del usuario
     */
    public Authentication authenticate(String username, String password) {
        // Cargar los detalles del usuario por su nombre
        UserDetails userDetails = this.loadUserByUsername(username);

        // Verificar si el usuario existe
        if (userDetails == null) {
            throw new BadCredentialsException("Nombre de usuario o contraseña inválidos");
        }

        // Verificar si la contraseña es correcta
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Contraseña incorrecta");
        }

        // Crear y devolver una autenticación con el nombre de usuario, contraseña y autoridades del usuario
        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }

    /**
     * Crea un nuevo usuario y devuelve un response.
     *
     * @param createRoleRequest el Request de creación de usuario
     * @return el response de autenticación
     */
    public AuthResponse createUser(AuthCreateUserRequest createRoleRequest) {

        String username = createRoleRequest.username();
        String name = createRoleRequest.name();
        String password = createRoleRequest.password();
        List<String> roleRequest = List.of("USER"); // Por defecto que sea user siempre que un usuario se registre

        // Buscar los roles en el repositorio por su nombre
        Set<RoleModel> roleModelList = roleRepository.findRoleModelsByRoleEnumIn(roleRequest)
                .stream()
                .collect(Collectors.toSet());

        // Verificar si los roles existen
        if (roleModelList.isEmpty()) {
            throw new IllegalArgumentException("Los roles especificados no existen.");
        }

        // Crear un nuevo usuario
        UserModel userModel = UserModel.builder()
                .username(username)
                .name(name)
                .password(passwordEncoder.encode(password))
                .roles(roleModelList)
                .isEnabled(true)
                .accountNoLocked(true)
                .accountNoExpired(true)
                .credentialNoExpired(true)
                .build();

        // Guardar el usuario en el repositorio
        UserModel userSaved = userRepository.save(userModel);

        // Crear una lista de autoridades para el usuario
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userSaved.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));
        userSaved.getRoles().stream().flatMap(role -> role.getPermissionList()
                        .stream())
                .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));

        // Crear una autenticación con el usuario, autoridades y sin contraseña
        Authentication authentication = new UsernamePasswordAuthenticationToken(userSaved, null, authorities);

        // Crear un token de acceso JWT
        String accessToken = jwtUtils.createToken(authentication);

        // Crear y devolver un response de autenticación
        AuthResponse authResponse = new AuthResponse(username, name , "Usuario creado exitosamente", (List<GrantedAuthority>) authentication.getAuthorities().stream().toList(), accessToken, true);
        return authResponse;
    }

    public String findNameByUsername(String username){
        return userRepository.findNameByUsername(username);
    }
}