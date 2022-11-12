package com.shopping.www.member.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberInfoRepository extends JpaRepository<MemberInfo, Long>{
	boolean existsByMemberId(String idCheck);

	boolean existsByMemberEmail(String emailCheck);

	Optional<MemberInfo> findByMemberId(String memberId);
}
