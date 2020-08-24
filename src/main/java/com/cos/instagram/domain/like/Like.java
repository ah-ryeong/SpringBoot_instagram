package com.cos.instagram.domain.like;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.instagram.domain.image.Image;
import com.cos.instagram.domain.image.Image.ImageBuilder;
import com.cos.instagram.domain.tag.Tag;
import com.cos.instagram.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Like {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private User user;
	@ManyToOne
	private Image image;
	
	@CreationTimestamp
	private Timestamp createDate;
}
