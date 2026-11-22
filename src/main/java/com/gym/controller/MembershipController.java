package com.gym.controller;

import com.gym.model.Membership;
import com.gym.service.MembershipService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memberships")
public class MembershipController {
	
	private final MembershipService membershipService;
	
	public MembershipController(MembershipService membershipService) {
		this.membershipService = membershipService;
	}
	
	@GetMapping
	public List<Membership> getMemberships() {
		return membershipService.getAllMemberships();
	}
	
	@GetMapping("/{id}")
	public Membership getMembershipById(@PathVariable Long id) {
		return membershipService.getMembershipById(id)
				.orElseThrow(() ->
					new RuntimeException("Membership not found"));
	}
	
	@PostMapping
	public Membership createMembership(@Valid @RequestBody Membership membership) {
		return membershipService.createMembership(membership);
	}
	
	@PutMapping("/{id}")
	public Membership updateMembership(
			@PathVariable Long id,
			@Valid @RequestBody Membership membership) {
		
		return membershipService.updateMembership(id,  membership);
	}
	
	@DeleteMapping("/{id}")
	public String deleteMembership(@PathVariable Long id) {
		membershipService.deleteMembership(id);
		return "Membership deleted successfully";
	}
	
	

}
