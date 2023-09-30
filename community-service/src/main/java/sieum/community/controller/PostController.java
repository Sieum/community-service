package sieum.community.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import sieum.community.dto.PostDetailDTO;
import sieum.community.dto.PostListDTO;
import sieum.community.dto.PostSaveDTO;
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

	@PostMapping
	public ResponseEntity<PostSaveDTO.Response> savePost(
			@RequestHeader(value="memberId") UUID memberId,
			PostSaveDTO.Request dto){
		System.out.println("여기 들어오니..?");
		dto.setMemberId(memberId);
		PostSaveDTO.Response res = postService.savePost(dto);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

}
