package com.cos.instagram.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.instagram.config.handler.ex.MyUsernameNotFoundException;

@Controller
public class IndexController {
	
	@GetMapping("/test/facebook")
	public @ResponseBody String facebook(
			Authentication authentication) {
		System.out.println("authentication : "+authentication.getPrincipal());
		System.out.println("authentication : "+authentication.getDetails());
		
		OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
		System.out.println("authentication : "+oAuth2User.getAttributes());
		//System.out.println("authentication : "+principalDetails.getUser());
		
		return "facebook 로그인완료";
	}
	
	@GetMapping("/test/facebook2")
	public @ResponseBody String facebook2(
			@AuthenticationPrincipal UserDetails principal) {
		System.out.println(principal.getUsername());
		return "facebook 로그인완료2";
	}

	@GetMapping("/test/login")
	public String test1() {
		return "auth/login";
	}
	
	@GetMapping("/test/join")
	public String test2() {
		return "auth/join";
	}
	
	@GetMapping("/test/following")
	public String test3() {
		return "follow/following";
	}
	
	@GetMapping("/test/explore")
	public String test4() {
		return "image/explore";
	}
	
	@GetMapping("/test/feed")
	public String test5() {
		return "image/feed";
	}
	
	@GetMapping("/test/upload")
	public String test6() {
		return "image/image-upload"; // 오류
	}
	
	@GetMapping("/test/proedit")
	public String test7() {
		return "user/profile_edit";
	}
	
	@GetMapping("/test/profile")
	public String test8() {
		return "user/profile"; 
	}
	
	@GetMapping("/test/username/{username}")
	public @ResponseBody String test9(@PathVariable String username) {
		if (!username.equals("ssar")) {
			throw new MyUsernameNotFoundException("유저네임 못 찾음");
		}
	
		return "username test";
	}
}
