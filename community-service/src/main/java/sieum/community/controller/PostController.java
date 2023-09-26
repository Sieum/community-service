package sieum.community.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sieum.community.dto.PostListDTO;
import sieum.community.service.PostService;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;


}
