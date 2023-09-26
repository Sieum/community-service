package sieum.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sieum.community.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
