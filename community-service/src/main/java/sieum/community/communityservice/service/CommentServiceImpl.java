package sieum.community.communityservice.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.hypersistence.utils.hibernate.util.StringUtils;
import lombok.RequiredArgsConstructor;
import sieum.community.communityservice.dto.CommentDeleteDTO;
import sieum.community.communityservice.dto.CommentListDTO;
import sieum.community.communityservice.dto.CommentSaveDTO;
import sieum.community.communityservice.dto.CommentUpdateDTO;
import sieum.community.communityservice.entity.Comment;
import sieum.community.communityservice.entity.Member;
import sieum.community.communityservice.entity.Post;
import sieum.community.communityservice.exception.AccessDeniedException;
import sieum.community.communityservice.exception.ErrorCode;
import sieum.community.communityservice.exception.NotFoundException;
import sieum.community.communityservice.exception.ValidationException;
import sieum.community.communityservice.repository.CommentRepository;
import sieum.community.communityservice.repository.MemberRepository;
import sieum.community.communityservice.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

	private final CommentRepository commentRepository;
	private final MemberRepository memberRepository;
	private final PostRepository postRepository;

	@Override
	@Transactional
	public CommentListDTO.Response getList(CommentListDTO.Request dto) {
		return null;
	}

	@Override
	@Transactional
	public CommentSaveDTO.Response save(CommentSaveDTO.Request dto) {
		UUID memberId = dto.getMemberId();
		Long postId = dto.getPostId();
		String content = dto.getContent();

		if (memberId == null || StringUtils.isBlank(content) || postId == null) {
			throw new ValidationException(ErrorCode.MISSING_INPUT_VALUE);
		}

		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new NotFoundException(ErrorCode.POST_NOT_FOUND));

		LocalDateTime createdDate = LocalDateTime.now();

		Comment comment = Comment.builder()
			.commentContent(content)
			.post(post)
			.member(member)
			.createdDate(createdDate)
			.modifiedDate(createdDate)
			.build();

		commentRepository.save(comment);

		return CommentSaveDTO.Response.builder()
			.success(true)
			.build();
	}

	@Override
	@Transactional
	public CommentUpdateDTO.Response update(CommentUpdateDTO.Request dto) {
		UUID memberId = dto.getMemberId();
		Long postId = dto.getPostId();
		Long commentId = dto.getCommentId();
		String content = dto.getContent();

		if(memberId == null || StringUtils.isBlank(content) || postId == null || commentId == null){
			throw new ValidationException(ErrorCode.MISSING_INPUT_VALUE);
		}

		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new NotFoundException(ErrorCode.POST_NOT_FOUND));

		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(()-> new NotFoundException(ErrorCode.COMMENT_NOT_FOUND));

		if(comment.getMember().getId() != memberId){
			throw new AccessDeniedException(ErrorCode.HANDLE_ACCESS_DENIED);
		}

		comment.updateContent(content);
		comment.updatedDate(LocalDateTime.now());

		return CommentUpdateDTO.Response.builder()
			.success(true)
			.build();
	}

	@Override
	@Transactional
	public CommentDeleteDTO.Response delete(CommentDeleteDTO.Request dto) {
		return null;
	}
}
