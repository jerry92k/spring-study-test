package com.jerry.springtest.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class MemberTest {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private EntityManager em;

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

	@Test
	void primitive타입_같은값으로_수정하면_dirty_bit_체크가_안되서_update_쿼리_안나간다(){
		Member member = new Member("jerry",10);
		Member persistMember = memberRepository.save(member);
		// memberRepository.flush();

		System.out.println("here");
		persistMember.changeAge(10);
		em.flush();
		em.clear();
		Member findMember = memberRepository.findById(persistMember.getId()).orElseThrow(EntityNotFoundException::new);
	}

	@Test
	void 객체타입도_같은값으로_수정하면_dirty_bit_체크가_안되서_update_쿼리_안나간다(){
		Member member = new Member("jerry",10);
		Member persistMember = memberRepository.save(member);
		// memberRepository.flush();

		System.out.println("here");
		persistMember.changeName(new String("jerry"));
		em.flush();
		em.clear();
		Member findMember = memberRepository.findById(persistMember.getId()).orElseThrow(EntityNotFoundException::new);
	}
}
