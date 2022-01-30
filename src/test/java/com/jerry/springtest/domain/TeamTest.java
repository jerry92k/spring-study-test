package com.jerry.springtest.domain;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class TeamTest {

	@Autowired
	private EntityManager entityManager;

	@Test
	void PersistentBag_test() {
		Team team = new Team("TeamA");

		System.out.println("before persist -- : " + team.getMembers().getClass());
		entityManager.persist(team);

		System.out.println("after persist -- : " + team.getMembers().getClass());
		entityManager.flush(); // 영속성 컨텍스트에 있는 것을 DB에 반영. 영속성 컨텍스트를 비우진 않음.
		entityManager.clear(); // 영속성 컨텍스트 비움

		Team findTeam = entityManager.find(Team.class, team.getId()); // clear를 호출했기 때문에 db에서 다시 찾음
		System.out.println("find team - member -- : " + findTeam.getMembers().getClass());
	}

	@Test
	void mappingtest() {
		Team team = new Team("TeamA");
		entityManager.persist(team);
		Member member1 = new Member("jerry");
		Member member2 = new Member("kim");
		member1.assignTeam(team);
		member2.assignTeam(team);
		entityManager.persist(member1);
		entityManager.persist(member2);
		entityManager.flush(); // 영속성 컨텍스트에 있는 것을 DB에 반영. 영속성 컨텍스트를 비우진 않음.
		// sql 확인
	}
}
