package com.shopping.www;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shopping.www.member.entity.MemberInfo;
import com.shopping.www.member.entity.MemberInfoRepository;

@SpringBootTest
public class MemberInfoTest {

	@Autowired
	MemberInfoRepository memberInfoRepository;
	
	@Test
	void save() {

		MemberInfo params = MemberInfo.builder()
				.memberId("test4")
				.memberPw("test4")
				.memberEmail("test4")
				.build();
		
		memberInfoRepository.save(params);
		
		MemberInfo entity = memberInfoRepository.findById(params.getMemberPk()).get();
		assertThat(entity.getMemberId()).isEqualTo("test4");
		assertThat(entity.getMemberPw()).isEqualTo("test4");
	}
	
	@Test
	void findAll() {
		
		Long membersCount = memberInfoRepository.count();
		
		List<MemberInfo> members = memberInfoRepository.findAll();
	}
	
	@Test
	void delete() {
		MemberInfo entity = memberInfoRepository.findById((long) 1).get();
		memberInfoRepository.deleteById((long) 1);
	}
}
