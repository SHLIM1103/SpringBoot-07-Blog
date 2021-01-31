package com.example.demo.sts.service;

import com.example.demo.uss.service.Student;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "grades")
public class Grade {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "grd_num") private int grdNum;
	@Column(name = "score") private int score;
	@Column(name = "grade") private String grade;
	@Column(name = "exam_date") private String examDate;
	@Column(name = "pass_exam") private String passExam;

	@ManyToOne
	@JoinColumn(name = "sub_num")
	private Subject subject;

	@ManyToOne
	@JoinColumn(name = "stu_num")
	private Student student;

	@Builder
	private Grade(int score, String grade, String examDate, String passExam) {
		this.score = score;
		this.grade = grade;
		this.examDate = examDate;
		this.passExam = passExam;
	}

	public Grade(String examDate, int score) {
		this.examDate = examDate;
		this.score = score;
	}
}