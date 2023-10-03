package sieum.community.communityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sieum.community.communityservice.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
