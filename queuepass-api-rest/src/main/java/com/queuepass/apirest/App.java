package com.queuepass.apirest;

import com.queuepass.apirest.model.security.PermissionModel;
import com.queuepass.apirest.model.security.RoleEnum;
import com.queuepass.apirest.model.security.RoleModel;
import com.queuepass.apirest.model.UserModel;
import com.queuepass.apirest.repository.UserRepository;
import com.queuepass.apirest.service.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository, StorageService storageService){
		return args -> {

			storageService.deleteAll();
			storageService.init();

			//CREATE PERMISSIONS
			PermissionModel createPermission = PermissionModel.builder()
					.name("CREATE")
					.build();
			PermissionModel readPermission = PermissionModel.builder()
					.name("READ")
					.build();
			PermissionModel deletePermission = PermissionModel.builder()
					.name("DELETE")
					.build();
			PermissionModel updatePermission = PermissionModel.builder()
					.name("UPDATE")
					.build();

			//CREATE ROLES
			RoleModel roleAdmin = RoleModel.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();
			RoleModel roleUser = RoleModel.builder()
					.roleEnum(RoleEnum.USER)
					.permissionList(Set.of(createPermission, readPermission))
					.build();
			//CREATE USERS
			UserModel userJavier = UserModel.builder()
					.email("Javier@gmail.com")
					.password(new BCryptPasswordEncoder().encode("1234"))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();
			UserModel userAlvaro = UserModel.builder()
					.email("Alvaro@gmail.com")
					.password(new BCryptPasswordEncoder().encode("1234"))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();

			userRepository.saveAll(List.of(userJavier, userAlvaro));
		};
	}
}
