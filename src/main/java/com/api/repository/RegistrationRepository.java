package com.api.repository;

import com.api.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;


//Here we are using long after the Registration because <EntityClassName,PrimaryKeyType>
//here the entity class is Registration and the primery key is id and the type of id is long so below is the result
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}