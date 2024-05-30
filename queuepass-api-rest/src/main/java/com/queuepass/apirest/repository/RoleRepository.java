package com.queuepass.apirest.repository;

import com.queuepass.apirest.model.security.RoleModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<RoleModel, Long> {
    List<RoleModel> findRoleModelsByRoleEnumIn(List<String> roleName); //Devuelve los roles que existen en la base de datos
                                                                       //Para evitar poner roles que no existen
}
