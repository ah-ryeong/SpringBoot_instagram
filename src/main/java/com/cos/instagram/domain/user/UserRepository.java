package com.cos.instagram.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 가 extends 되면 @Repository 필요없음 IoC 자동으로 된다.
public interface UserRepository extends JpaRepository<User, Integer> {

}
