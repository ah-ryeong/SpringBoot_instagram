package com.cos.instagram.domain.image;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.instagram.domain.tag.Tag;
import com.cos.instagram.domain.user.User;
import com.cos.instagram.web.dto.UserProfileImageRespDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SqlResultSetMapping(
		name = "UserProfileImageRespDtoMapping",
		classes = @ConstructorResult(
				targetClass = UserProfileImageRespDto.class,
				columns = {
						@ColumnResult(name="id", type = Integer.class),
						@ColumnResult(name="imageUrl", type = String.class),
						@ColumnResult(name="likeCount", type = Integer.class),
						@ColumnResult(name="commentCount", type = Integer.class)
				}
				)
		)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String location;
	private String caption; // content 설명
	@Column(length = 10000)
	private String imageUrl; // 이미지경로
	
	// Image를 select하면 한명의 User가 딸려옴
	// 1명만 들어오니까 시스템의 부하가 적음 , EAGER :join해서 가져옴
	@ManyToOne(fetch = FetchType.EAGER) // 생략해도 eager임
	@JoinColumn(name="userId") // findById하면 바로 join문 실행됨
	private User user; // int userId 가 아닌 Object를 넣으면 1이 안 나오고 해당 Object를 select해서 들고옴
	// ↑ 타입은 User오브젝트의 PK가 타입이 된다 -> int 
	
	// FetchType은 디폴트가 LAZY임 , LAZY : 셀렉트를 안 함 null 값 들어감, getter를 호출하면 select한다.
	// 장점 : 필요하면 쓰고 필요없으면 안 쓴다 -> 부하가 적음 
	@OneToMany(mappedBy = "image", fetch = FetchType.LAZY) // 연관관계의 주인의 변수명을 적는다.
	@JsonIgnoreProperties({"image"})
	private List<Tag> tags;
	
	@CreationTimestamp
	private Timestamp createDate;
}
