package com.cos.instagram.web;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.instagram.config.auth.LoginUser;
import com.cos.instagram.config.auth.LoginUserAnnotation;
import com.cos.instagram.config.auth.PrincipalDetails;

@Controller
public class ImageController {

	@GetMapping({"", "/", "/image/feed"})
	public String feed(@LoginUserAnnotation LoginUser loginUser) {
		System.out.println("loginUser : " + loginUser);
		return "image/feed";
	}
}
