package com.coursesolvve.webproject.repository;

import com.coursesolvve.webproject.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends CrudRepository<Role, UUID> {

}
