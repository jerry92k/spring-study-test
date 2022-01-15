package com.jerry.springtest.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "team")
	private List<Member> members = new ArrayList<>();

	protected Team() {
	}

	public Team(String name) {
		this.name = name;
	}

	public void addMember(Member member){
		members.add(member);
		member.assignTeam(this);
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
