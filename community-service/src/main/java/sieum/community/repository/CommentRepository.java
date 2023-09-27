package sieum.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sieum.community.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
