package sieum.community.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@SQLDelete(sql = "UPDATE comment SET comment_is_deleted = TRUE WHERE comment_id = ?")
@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Long commentId;

	@Column(name = "comment_content", length = 50, unique = false, nullable = false)
	private String commentContent;

	@Column(name = "member_id", columnDefinition = "BINARY(16)")
	private UUID memberId;

	@Column(name = "post_id")
	private Long postId;

	@CreatedDate
	@Column(name = "comment_created_date")
	private LocalDateTime commentCreatedDate;

	@LastModifiedDate
	@Column(name = "commment_modified_date")
	private LocalDateTime commentModifiedDate;

	@Column(name = "comment_is_deleted")
	private boolean commentIsDeleted;
}
