package com.gym.service;

import com.gym.model.Membership;
import com.gym.repository.MembershipRepository;
import org.springframework.stereotype.Service;
import com.gym.exception.DuplicateResourceException;
import com.gym.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class MembershipService {
	
	private final MembershipRepository membershipRepository;
	
	public MembershipService(MembershipRepository membershipRepository) {
		this.membershipRepository = membershipRepository;
	}
	
	public List<Membership> getAllMemberships() {
		return membershipRepository.findAll();
	}
	
	public Membership createMembership(Membership membership) {
		return membershipRepository.save(membership);
	}
	
	public Optional<Membership> getMembershipById(Long id) {
		return membershipRepository.findById(id);
	}
	
	public Membership updateMembership(Long id, Membership updatedMembership) {
		
		return membershipRepository.findById(id)
				.map(existing -> {
					
					existing.setMembershipType(updatedMembership.getMembershipType());
					existing.setPrice(updatedMembership.getPrice());
					existing.setDurationMonths(
							updatedMembership.getDurationMonths()
					);
					
					return membershipRepository.save(existing);
				})
				.orElseThrow(() ->
					new ResourceNotFoundException(
						"Membership not found with ID: " + id));
	}
	
	public void deleteMembership(Long id) {
		membershipRepository.deleteById(id);
	}

}
