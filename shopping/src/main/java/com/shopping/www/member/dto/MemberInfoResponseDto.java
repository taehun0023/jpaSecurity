package com.shopping.www.member.dto;

import java.time.LocalDateTime;

import com.shopping.www.member.entity.MemberInfo;

import lombok.Getter;

@Getter
public class MemberInfoResponseDto {
	
	private Long memberPk;
	private String memberId;
	private String memberPw;
	private String memberEmail;
	private String role;
	private LocalDateTime memberDate = LocalDateTime.now();
	private LocalDateTime memberUpdate;
	
	public MemberInfoResponseDto(MemberInfo entity) {
		this.memberPk = entity.getMemberPk();
		this.memberId = entity.getMemberId();
		this.memberPw = entity.getMemberPw();
		this.memberEmail = entity.getMemberEmail();
		this.role = entity.getRole();
		this.memberDate = entity.getMemberDate();
		this.memberUpdate = entity.getMemberUpdate();
	}

}
