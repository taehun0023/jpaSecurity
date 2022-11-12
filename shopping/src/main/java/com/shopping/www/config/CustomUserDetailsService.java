package com.shopping.www.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shopping.www.member.entity.MemberInfo;
import com.shopping.www.member.entity.MemberInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	
	private final MemberInfoRepository memberInfoRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<MemberInfo> memberInfo = memberInfoRepository.findByMemberId(username);
		System.out.println("memberInfo" + memberInfo);
		
		if (memberInfo.isEmpty()) {
			throw new UsernameNotFoundException("해당 이용자가 존재하지 않습니다.");
		}
		
		MemberInfo result = memberInfo.get();
		System.out.println("result" + result);
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(result.getRole()));
		
		return new CustomUserDetails(result, roles);
	}
}
