package com.jerry.springtest.domain;

import static org.assertj.core.api.Assertions.*;

import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class MemberTest {

	@Autowired
	private MemberRepository memberRepository;

	@Test
	void spring_data_jpa_query_annotation_select전에_flush(){
		Member member = new Member("jerry");
		member.changeAge(10);
		Member savedMember = memberRepository.save(member);
		assertThat(savedMember.getAge()).isEqualTo(10);
		member.changeAge(20);
		Member findMemberByCustomName = memberRepository.findCustomName("jerry").orElseThrow(EntityNotFoundException::new);
		assertThat(findMemberByCustomName.getAge()).isEqualTo(20);
	}

	@Test
	void spring_data_jpa_by_not_identifier_select전에_flush(){
		Member member = new Member("jerry");
		member.changeAge(10);
		Member savedMember = memberRepository.save(member);
		assertThat(savedMember.getAge()).isEqualTo(10);
		member.changeAge(20);
		Member findMemberByName = memberRepository.findByName("jerry").orElseThrow(EntityNotFoundException::new);
		assertThat(findMemberByName.getAge()).isEqualTo(20);
	}

	@Test
	void spring_data_jpa_by_identifier_select전에_flush(){
		Member member = new Member("jerry");
		member.changeAge(10);
		Member savedMember = memberRepository.save(member);
		assertThat(savedMember.getAge()).isEqualTo(10);
		member.changeAge(20);
		Member findMemberById = memberRepository.findById(member.getId()).orElseThrow(EntityNotFoundException::new);
		assertThat(findMemberById.getAge()).isEqualTo(20);
	}
}
