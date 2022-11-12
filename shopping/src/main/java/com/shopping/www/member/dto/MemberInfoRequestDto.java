package com.shopping.www.member.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.shopping.www.member.entity.MemberInfo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberInfoRequestDto {
	
	private PasswordEncoder encoder; 
	
	private String memberId;
	private String memberPw;
	private String memberEmail;
	private String role;
	
	public MemberInfo toEntity() {
		return MemberInfo.builder()
				.memberId(memberId)
				.memberPw(encoder.encode(memberPw))
				.memberEmail(memberEmail)
				.role(role)
				.build();
	}
}
