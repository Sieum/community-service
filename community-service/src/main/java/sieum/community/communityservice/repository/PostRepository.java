package sieum.community.communityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sieum.community.communityservice.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
