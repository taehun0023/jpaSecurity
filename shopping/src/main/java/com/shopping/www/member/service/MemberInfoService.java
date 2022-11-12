package com.shopping.www.member.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.www.exception.CustomException;
import com.shopping.www.exception.ErrorCode;
import com.shopping.www.member.dto.MemberInfoRequestDto;
import com.shopping.www.member.dto.MemberInfoResponseDto;
import com.shopping.www.member.entity.MemberInfo;
import com.shopping.www.member.entity.MemberInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberInfoService {

	private final MemberInfoRepository memberRepository;
	
	// 회원가입
	@Transactional
	public Long signUp(MemberInfoRequestDto params) {
		
		MemberInfo entity = memberRepository.save(params.toEntity());
		return entity.getMemberPk();
	}
	
	// ID중복 체크
	@Transactional
	public boolean idCheck(String idCheck) {
		return memberRepository.existsByMemberId(idCheck);
		
	}
	
	// 영속성 컨텍스트! 즉 더티 체킹
	// Entity에 변화가 생기면 자동으로 트랜잭션이 commit되는 시점에 update 쿼리를 실행 하기 때문에
	// 따로 update 쿼리문이 없다.
	@Transactional
	public String memberUpdate(Long memberPk, MemberInfoRequestDto params) {
		
		MemberInfo entity = memberRepository.findById(memberPk).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
		entity.memberUpdate(params.getMemberPw(), params.getMemberEmail());
		return entity.getMemberId();
	}

	@Transactional
	public boolean emailCheck(String emailCheck) {
		return memberRepository.existsByMemberEmail(emailCheck);
	}
}
