package com.gym.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.model.Manager;

@Repository
public interface ManagerRepository
		extends JpaRepository<Manager, Long> {

	Optional<Manager> findByEmail(String email);

	boolean existsByEmail(String email);
}
