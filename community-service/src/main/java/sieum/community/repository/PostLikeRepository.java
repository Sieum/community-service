package sieum.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sieum.community.entity.PostLike;
import sieum.community.entity.PostLikeKey;

public interface PostLikeRepository extends JpaRepository<PostLike, PostLikeKey> {
}
