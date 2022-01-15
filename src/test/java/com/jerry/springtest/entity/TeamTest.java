package com.jerry.springtest.entity;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class TeamTest {

	@Autowired
	private EntityManager entityManager;

	@Test
	void test() {
		Team team = new Team("TeamA");

		System.out.println("before persist -- : " + team.getMembers().getClass());
		entityManager.persist(team);

		System.out.println("after persist -- : " + team.getMembers().getClass());
		entityManager.flush(); // 영속성 컨텍스트에 있는 것을 DB에 반영. 영속성 컨텍스트를 비우진 않음.
		entityManager.clear(); // 영속성 컨텍스트 비움

		Team findTeam = entityManager.find(Team.class, team.getId()); // clear를 호출했기 때문에 db에서 다시 찾음
		System.out.println("find team - member -- : " + findTeam.getMembers().getClass());
	}
}
