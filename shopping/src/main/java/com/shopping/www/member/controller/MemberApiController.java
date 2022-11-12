package com.shopping.www.member.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shopping.www.member.dto.MemberInfoRequestDto;
import com.shopping.www.member.service.MemberInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/member")
@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberApiController {

	private final MemberInfoService memberService;
	
	@GetMapping(value = "/signUp")
	public ModelAndView signUpPage() {
		ModelAndView mav = new ModelAndView("member/signUp");
		return mav;
	}
	
	@PostMapping("/idCheck")
	public ResponseEntity<Boolean> idCheck(@RequestParam String idCheck) {
		log.info(idCheck);
		return ResponseEntity.ok(memberService.idCheck(idCheck));
	}
	
	@GetMapping(value = "/emailCheck")
	public ResponseEntity<Boolean> emailCheck(@RequestParam String emailCheck) {
		log.info(emailCheck);
		return ResponseEntity.ok(memberService.emailCheck(emailCheck));
	}
	
	@PostMapping(value = "/signUp")
	public Long signUp(@RequestBody MemberInfoRequestDto params) {
		return memberService.signUp(params);
	}
	
	
	@GetMapping(value = "/findUserInfo")
	public ModelAndView findUserInfo() {
		ModelAndView mav = new ModelAndView("member/findUserInfo");
		return mav;
	}
	
}
