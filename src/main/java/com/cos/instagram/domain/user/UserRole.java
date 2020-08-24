package com.cos.instagram.domain.user;

import lombok.Getter;

@Getter
public enum UserRole { // 만약에 회원(작가, 비작가) 할거면 USER("작가"), USER("비작가")
	USER("ROLE_USER"), ADMIN("ROLE_ADMIN");
	
	UserRole(String name) {
		this.name = name;
	}
	
	private String name;
}
