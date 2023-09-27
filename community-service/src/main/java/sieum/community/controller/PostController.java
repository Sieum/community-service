package sieum.community.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sieum.community.dto.PostDetailDTO;
import sieum.community.dto.PostListDTO;
import sieum.community.service.PostService;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@GetMapping(value = "/{postId}")
	public ResponseEntity<PostDetailDTO.Response> getPost(
		@RequestHeader(value="memberId") UUID memberId,
		@PathVariable Long postId){
		PostDetailDTO.Request dto = PostDetailDTO.Request.builder()
			.memberId(memberId)
			.postId(postId)
			.build();
		PostDetailDTO.Response post = postService.getPost(dto);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}
}
