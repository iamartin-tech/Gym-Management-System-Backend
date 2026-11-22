package com.gym.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.model.Administrator;

@Repository
public interface AdministratorRepository
		extends JpaRepository<Administrator, Long> {

	Optional<Administrator> findByEmail(String email);

	boolean existsByEmail(String email);
}
