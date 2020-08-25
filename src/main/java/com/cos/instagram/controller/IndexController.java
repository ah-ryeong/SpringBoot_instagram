package com.cos.instagram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.instagram.config.handler.MyUsernameNotFoundException;

@Controller
public class IndexController {

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
