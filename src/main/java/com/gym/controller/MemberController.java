package com.gym.controller;

import com.gym.model.Member;
import com.gym.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
	
	private final MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping
	public List<Member> getMembers() {
		return memberService.getAllMembers();
	}
	
	@GetMapping("/{id}")
	public Member getMemberById(@PathVariable Long id) {
		
		return memberService.getMemberById(id)
				.orElseThrow(() ->
					new RuntimeException("Member not found"));
	}
	
	@PostMapping
	public Member createMember(@Valid @RequestBody Member member) {
		return memberService.createMember(member);
	}
	
	@PutMapping("/{id}")
	public Member updateMember(
			@PathVariable Long id,
			@Valid @RequestBody Member member) {
		
		return memberService.updateMember(id,  member);
	}
	
	@DeleteMapping("/{id}")
	public String deleteMember(@PathVariable Long id) {
		memberService.deleteMember(id);
		
		return "Member deleted successfully";
	}

}
