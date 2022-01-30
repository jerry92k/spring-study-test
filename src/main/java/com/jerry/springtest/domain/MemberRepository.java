package com.jerry.springtest.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member,Long> {

	Optional<Member> findByName(String name);

	@Query(value = "select m from Member m "+
	"where m.name=:name")
	Optional<Member> findCustomName(@Param("name") String name);
}
