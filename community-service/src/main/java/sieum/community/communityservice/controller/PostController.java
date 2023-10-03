package sieum.community.communityservice.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import sieum.community.communityservice.dto.PostDetailDTO;
import sieum.community.communityservice.dto.PostSaveDTO;
import sieum.community.communityservice.service.PostService;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@GetMapping(value = "/{postId}")
	public ResponseEntity<PostDetailDTO.Response> getPost(
		@RequestHeader(name="uuid") String memberId,
		@PathVariable Long postId){
		PostDetailDTO.Request dto = PostDetailDTO.Request.builder()
			.memberId(UUID.fromString(memberId))
			.postId(postId)
			.build();
		PostDetailDTO.Response post = postService.getPost(dto);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<PostSaveDTO.Response> savePost(
			@RequestHeader(name="uuid") String memberId,
			@RequestBody PostSaveDTO.Request dto){
		dto.setMemberId(UUID.fromString(memberId));
		PostSaveDTO.Response res = postService.savePost(dto);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

}
