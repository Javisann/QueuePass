package com.queuepass.apirest;

import com.queuepass.apirest.model.CompanyModel;
import com.queuepass.apirest.model.PlateModel;
import com.queuepass.apirest.model.QueueModel;
import com.queuepass.apirest.model.UserModel;
import com.queuepass.apirest.model.security.PermissionModel;
import com.queuepass.apirest.model.security.RoleModel;
import com.queuepass.apirest.repository.CompanyRepository;
import com.queuepass.apirest.repository.PlateRepository;
import com.queuepass.apirest.repository.QueueRepository;
import com.queuepass.apirest.service.storage.StorageService;
import com.queuepass.apirest.model.security.RoleEnum;
import com.queuepass.apirest.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, StorageService storageService, CompanyRepository companyRepository, PlateRepository plateRepository, QueueRepository queueRepository) {
        return args -> {

			/*
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
					.username("Javier@gmail.com")
					.password(new BCryptPasswordEncoder().encode("1234"))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();
			UserModel userAlvaro = UserModel.builder()
					.username("Alvaro@gmail.com")
					.password(new BCryptPasswordEncoder().encode("1234"))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();
			UserModel userDavid = UserModel.builder()
					.username("David@gmail.com")
					.password(new BCryptPasswordEncoder().encode("1234"))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();
			UserModel userDaniel = UserModel.builder()
					.username("Daniel@gmail.com")
					.password(new BCryptPasswordEncoder().encode("4321"))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();

			userRepository.saveAll(List.of(userJavier, userAlvaro, userDavid, userDaniel));*/
        };
    }
}
