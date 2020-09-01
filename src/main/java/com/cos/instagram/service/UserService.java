package com.cos.instagram.service;

import java.util.List;
import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.instagram.config.auth.dto.LoginUser;
import com.cos.instagram.config.handler.ex.MyUserIdNotFoundException;
import com.cos.instagram.domain.image.ImageRepository;
import com.cos.instagram.domain.user.User;
import com.cos.instagram.domain.user.UserRepository;
import com.cos.instagram.web.dto.JoinReqDto;
import com.cos.instagram.web.dto.UserProfileImageRespDto;
import com.cos.instagram.web.dto.UserProfileRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	@PersistenceContext
	EntityManager em;
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final ImageRepository imageRepository;
	
	@Transactional  // 트랜젝션 시작
	public void 회원가입(JoinReqDto joinReqDto) {
		String encPassword =
		bCryptPasswordEncoder.encode(joinReqDto.getPassword());
		joinReqDto.setPassword(encPassword);
		
		// joinReqDto를 UserEntity로 바꾸는 작업이 필요함
		userRepository.save(joinReqDto.toEntity()); // db커넥션 유지
	}
	
	// 읽기 전용 트랜잭션
	// 1. 변경 감지 연산을 하지 않는다.
	// 2. isolation(고립성)을 위해 Phantom read 문제가 일어나지 않는다.
	@Transactional(readOnly = true)
	public UserProfileRespDto 회원프로필(int id, LoginUser loginUser) {

		int imageCount;
		int followerCount;
		int followingCount;
		boolean followState;
		
		User userEntity = userRepository.findById(id)
				.orElseThrow(new Supplier<MyUserIdNotFoundException>() {

					@Override
					public MyUserIdNotFoundException get() {
						return new MyUserIdNotFoundException();
					}
				});
		// 1. 이미지들과 전체 이미지 카운트
		String q = "select im.id as id, im.imageUrl as imageUrl, im.userId as userId, (select count(*) from likes lk where lk.imageId = im.id) as likeCount, (select count(*) from comment ct where ct.imageId = im.id) as commentCount from image im where im.userId = ?";
		Query query = em.createNativeQuery(q, "UserProfileImageRespDtoMapping").setParameter(1, id);
		List<UserProfileImageRespDto> imagesEntity = query.getResultList();
		
		imageCount = imagesEntity.size();
		
		// 2. 팔로우 수 (수정해야한다)
		followerCount = 50;
		followingCount = 100;
		
		// 3. 최종 마무리
		UserProfileRespDto userProfileRespDto = 
				UserProfileRespDto.builder()
					.pageHost(id==loginUser.getId())
					.followerCount(followerCount)
					.followingCount(followingCount)
					.imageCount(imageCount)
					.user(userEntity)
					.images(imagesEntity) // 수정완료(Dto 만듬) (댓글수, 좋아요 수)
					.build();
		
		return userProfileRespDto;
	}
}
