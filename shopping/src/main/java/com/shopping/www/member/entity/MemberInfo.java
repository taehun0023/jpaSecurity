package com.shopping.www.member.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MemberInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberPk;
	private String role;
	private String memberId;
	private String memberPw;
	private String memberEmail;
	private LocalDateTime memberDate = LocalDateTime.now();
	private LocalDateTime memberUpdate;

	@Builder
	public MemberInfo(String role, String memberId, String memberPw, String memberEmail) {
		this.role = role;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberEmail = memberEmail;
	}
	
	public void memberUpdate(String memberPw, String memberEmail) {
		this.memberPw = memberPw;
		this.memberEmail = memberEmail;
	}
}
