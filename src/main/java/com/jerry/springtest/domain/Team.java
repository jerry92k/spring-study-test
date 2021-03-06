package com.jerry.springtest.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
	private List<Member> members = new ArrayList<>();

	protected Team() {
	}

	public Team(String name) {
		this.name = name;
	}

	public void addMember(Member member){
		members.add(member);
	}

	public List<Member> getMembers() {
		return members;
	}

	public Long getId() {
		return id;
	}

	public void clearMembers() {
		members = new ArrayList<>();
	}
}
