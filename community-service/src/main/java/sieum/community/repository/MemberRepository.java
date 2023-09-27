package sieum.community.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import sieum.community.entity.Member;

public interface MemberRepository extends JpaRepository<Member, UUID> {
}
