package com.shopping.www.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.shopping.www.member.entity.MemberInfo;

public class CustomUserDetails extends User{

	private final MemberInfo memberInfo;

	public CustomUserDetails(MemberInfo memberInfo, Collection<? extends GrantedAuthority> authorities) {
		super(memberInfo.getMemberId(), memberInfo.getMemberPw(), authorities);
		
		this.memberInfo = memberInfo;
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

}