package com.gym.service;

import com.gym.model.Member;
import com.gym.model.Membership;
import com.gym.repository.MemberRepository;
import com.gym.repository.MembershipRepository;
import com.gym.exception.DuplicateResourceException;
import com.gym.exception.ResourceNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
	
	private final PasswordEncoder passwordEncoder;
	private final MemberRepository memberRepository;
	private final MembershipRepository membershipRepository;
	
	
	public MemberService(
			MemberRepository memberRepository,
			MembershipRepository membershipRepository,
			PasswordEncoder passwordEncoder) {
		
		this.memberRepository = memberRepository;
		this.membershipRepository = membershipRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	// Get all members
	public List<Member> getAllMembers() {
		return memberRepository.findAll();
	}
	
	// Create a member
	public Member createMember(Member member) {

		if (memberRepository.existsByEmail(member.getEmail())) {
			throw new DuplicateResourceException(
					"Email is already in use");
		}

		if (member.getMembership() != null
				&& member.getMembership().getMembershipId() != null) {

			Long membershipId =
					member.getMembership().getMembershipId();

			Membership membership =
					membershipRepository.findById(membershipId)
						.orElseThrow(() ->
							new ResourceNotFoundException(
									"Membership not found with ID: "
									+ membershipId));

			member.setMembership(membership);
		}

		member.setPassword(
				passwordEncoder.encode(member.getPassword()));

		return memberRepository.save(member);
	}
	
	// Get a member by ID
	public Optional<Member> getMemberById(Long id) {
		return memberRepository.findById(id);
	}
	
	// Update member
	public Member updateMember(
			Long id,
			Member updatedMember) {

		Member existing = memberRepository.findById(id)
				.orElseThrow(() ->
					new ResourceNotFoundException(
							"Member not found with ID: " + id));

		if (!existing.getEmail().equals(updatedMember.getEmail())
				&& memberRepository.existsByEmail(
						updatedMember.getEmail())) {

			throw new DuplicateResourceException(
					"Email is already in use");
		}

		existing.setFirstName(updatedMember.getFirstName());
		existing.setLastName(updatedMember.getLastName());
		existing.setEmail(updatedMember.getEmail());
		existing.setPhoneNumber(updatedMember.getPhoneNumber());

		if (updatedMember.getMembership() != null
				&& updatedMember.getMembership()
						.getMembershipId() != null) {

			Long membershipId =
					updatedMember.getMembership()
						.getMembershipId();

			Membership membership =
					membershipRepository.findById(membershipId)
						.orElseThrow(() ->
							new ResourceNotFoundException(
									"Membership not found with ID: "
									+ membershipId));

			existing.setMembership(membership);
		}

		return memberRepository.save(existing);
	}
	
	// Delete member
	public void deleteMember(Long id) {

		Member member = memberRepository.findById(id)
				.orElseThrow(() ->
					new ResourceNotFoundException(
							"Member not found with ID: " + id));

		memberRepository.delete(member);
	}

}
