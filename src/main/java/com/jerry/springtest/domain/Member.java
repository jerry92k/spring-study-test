package com.jerry.springtest.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Member {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@JoinColumn(name = "team_id")
	@ManyToOne
	private Team team;

	protected Member() {
	}

	public Member(String name) {
		this.name = name;
	}

	public void assignTeam(Team team){
		this.team=team;
		team.addMember(this);
	}
}
