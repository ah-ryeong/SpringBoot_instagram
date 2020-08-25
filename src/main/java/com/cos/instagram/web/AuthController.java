package com.cos.instagram.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.instagram.domain.user.User;
import com.cos.instagram.service.UserService;
import com.cos.instagram.web.dto.JoinReqDto;

import lombok.RequiredArgsConstructor;

// 인증되지 않은 사용자들이 들어오는 페이지 : 회원가입, 로그인
@RequiredArgsConstructor
@Controller
public class AuthController {

	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	private final UserService userService;
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		log.info("/auth/loginForm 진입"); 
		return "auth/loginForm";
	}
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		log.info("/auth/joinForm 진입");
		return "auth/joinForm";
	}
	
	@PostMapping("/auth/join")
	public String join(JoinReqDto joinReqDto) { // ﻿Json으로 날아오는게 아니라 form으로 날아온다 -> form은 스프링에서 파싱 가능
		
		log.info(joinReqDto.toString());
		userService.회원가입(joinReqDto);
		return "redirect:/auth/loginForm"; //﻿메인페이지가 feed가 될거기 때문에 redirect 주소를 feed로 넣어준다.
	}
}
