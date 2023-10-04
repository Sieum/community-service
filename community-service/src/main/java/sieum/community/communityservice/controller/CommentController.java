package sieum.community.communityservice.controller;

import java.nio.file.Path;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sieum.community.communityservice.dto.CommentSaveDTO;
import sieum.community.communityservice.service.CommentService;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;

	@PostMapping(value = "/{postId}/comments")
	public ResponseEntity<CommentSaveDTO.Response> saveComment(
		@RequestHeader(name = "uuid") String memberId,
		@PathVariable Long postId,
		@Validated @RequestBody CommentSaveDTO.Request dto){
		dto.setMemberId(UUID.fromString(memberId));
		dto.setPostId(postId);
		return new ResponseEntity<>(commentService.save(dto), HttpStatus.CREATED);
	}

}
