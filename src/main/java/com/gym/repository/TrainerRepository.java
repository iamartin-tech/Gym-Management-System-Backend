package com.gym.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.model.Trainer;

@Repository
public interface TrainerRepository
		extends JpaRepository<Trainer, Long> {

	Optional<Trainer> findByEmail(String email);

	boolean existsByEmail(String email);
}
