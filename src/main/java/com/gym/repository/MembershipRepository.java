package com.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.model.Membership;

import java.util.Optional;

@Repository
public interface MembershipRepository 
	extends JpaRepository<Membership, Long> {
	
	Optional<Membership> findByMembershipType(String membershipType);

}
