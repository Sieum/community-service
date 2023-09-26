package sieum.community.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post")
@SQLDelete(sql = "UPDATE post SET post_is_deleted = TRUE WHERE post_id = ?")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long postId;

	@Column(name = "member_id", columnDefinition = "BINARY(16)")
	private UUID memberId;

	@Column(name = "postContent", length = 500, unique = false, nullable = true, columnDefinition = "TEXT")
	private String postContent;

	@Column(name = "post_music_uri")
	private String postMusicUri;

	@Column(name = "post_artist")
	private String postArtist;

	@Column(name = "post_title")
	private String postTitle;

	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Builder.Default
	private List<CommentEntity> comments = new ArrayList<>();

	@CreatedDate
	@Column(name = "post_created_date", updatable = false)
	private LocalDateTime postCreatedDate;

	@LastModifiedDate
	@Column(name = "post_modified_date")
	private LocalDateTime postModifiedDate;

	@Column(name = "post_is_deleted")
	private boolean postIsDeleted;
}
