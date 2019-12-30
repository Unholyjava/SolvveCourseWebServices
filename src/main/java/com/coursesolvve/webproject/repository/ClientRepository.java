package com.coursesolvve.webproject.repository;

import com.coursesolvve.webproject.domain.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ClientRepository extends CrudRepository<Client, UUID> {

}
