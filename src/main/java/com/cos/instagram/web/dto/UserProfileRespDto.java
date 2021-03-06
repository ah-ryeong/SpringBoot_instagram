package com.cos.instagram.web.dto;

import java.util.List;

import com.cos.instagram.domain.image.Image;
import com.cos.instagram.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileRespDto {

	private boolean pageHost; // 페이지의 주인 확인
	private boolean followState;
	private User user;
	private List<UserProfileImageRespDto> images;
	private int followerCount;
	private int followingCount;
	private int imageCount;
}
