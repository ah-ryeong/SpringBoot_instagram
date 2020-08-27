package com.cos.instagram.config.auth;

import java.util.function.Function;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.instagram.domain.user.User;
import com.cos.instagram.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

	
	private static final Logger log = LoggerFactory.getLogger(PrincipalDetailsService.class);
	private final UserRepository userRepository;
	private final HttpSession session;
	
	// Security Session > Authentication > UserDetails
	// 해당 함수가 정상적으로 리턴되면 @AuthenticationPrincipal 어노테이션 활성화된다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("loadUserByUsername : username : " + username);
		
		User userEntity = userRepository.findByUsername(username)
				.map(new Function<User, User>() {

					@Override
					public User apply(User t) {
						session.setAttribute("loginUser", new LoginUser(t));

						return t;
					}
					
				}).orElse(null);
		
		return new PrincipalDetails(userEntity);
	}
	
	
	
}
