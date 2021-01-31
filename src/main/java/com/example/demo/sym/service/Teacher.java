package com.example.demo.sym.service;

import com.example.demo.sts.service.Subject;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "teachers")
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tea_num") private int teaNum;
	@Column(name = "name") private String name;
	@Column(name = "password") private String password;
	@Column(name = "profile_image") private String profileImage;

	@ManyToOne
	@JoinColumn(name = "sub_num")
	private Subject subject;

	@Builder
	public Teacher(String name, String password, String profileImage) {
		this.name = name;
		this.password = password;
		this.profileImage = profileImage;
	}

	public Teacher(int teaNum, String name, String password, String profileImage) {
		this.teaNum = teaNum;
		this.name = name;
		this.password = password;
		this.profileImage = profileImage;
	}
}