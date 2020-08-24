package com.cos.instagram.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.instagram.domain.image.Image;
import com.cos.instagram.domain.image.ImageRepository;
import com.cos.instagram.domain.tag.Tag;
import com.cos.instagram.domain.tag.TagRepository;
import com.cos.instagram.domain.user.User;
import com.cos.instagram.domain.user.UserRepository;
import com.cos.instagram.domain.user.UserRole;

@RestController
public class TestApiController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private TagRepository tagRepository;
	
	@GetMapping("/test/api/join")
	public User join() {
		
		User user = User.builder()
				.name("Winter")
				.password("1234")
				.phone("01012341234")
				.bio("안녕")
				.role(UserRole.USER)
				.build();
		User userEntity = userRepository.save(user);
		return userEntity;
	}
	
	@GetMapping("test/api/image")
	public String image() {
		
		User userEntity = userRepository.findById(1).get(); // 세션에서 들고옴
		
		Image image = Image.builder()
				.location("다낭")
				.caption("설명")
				.user(userEntity)
				.build();
		
		// 이미지를 먼저 save 해서 이미지를 영속화시켰음
		Image imageEntity = imageRepository.save(image);
		
		List<Tag> tags = new ArrayList<>();
		Tag tag1 = Tag.builder()
				.name("#다낭")
				.image(imageEntity)
				.build();
		Tag tag2 = Tag.builder()
				.name("#여행")
				.image(imageEntity)
				.build();
		
		tags.add(tag1);
		tags.add(tag2);
		
		tagRepository.saveAll(tags);
		
//		List<Image> images = imageRepository.findAll();
		
//		Image imageEntity = imageRepository.findById(1).get();
		return "Image Insert 잘 됨"; // MessageConverter의 Jackson이 발동
		// Jackson : Java Object를 Json으로 바꿔서 리턴해주는애
	}
	
	@GetMapping("/test/api/image/list")
	public List<Image> imageList() {
		return imageRepository.findAll();
	}
	
	@GetMapping("/test/api/tag/list")
	public List<Tag> tagList() {
		return tagRepository.findAll();
	}
}
