package sieum.community.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sieum.community.dto.PostDeleteDTO;
import sieum.community.dto.PostDetailDTO;
import sieum.community.dto.PostLikeDTO;
import sieum.community.dto.PostListDTO;
import sieum.community.dto.PostSaveDTO;
import sieum.community.dto.PostUpdateDTO;
import sieum.community.entity.Member;
import sieum.community.entity.Post;
import sieum.community.entity.PostLike;
import sieum.community.entity.PostLikeKey;
import sieum.community.exception.ErrorCode;
import sieum.community.exception.NotFoundException;
import sieum.community.repository.MemberRepository;
import sieum.community.repository.PostLikeRepository;
import sieum.community.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

	private final PostRepository postRepository;
	private final MemberRepository memberRepository;
	private final PostLikeRepository postLikeRepository;

	@Override
	public PostListDTO.Response getAllPosts(PostListDTO.Request dto) {
		int page = dto.getPage();
		String filter = dto.getFilter();
		UUID memberId = dto.getMemberId();
		return null;
	}

	@Override
	public PostDetailDTO.Response getPost(PostDetailDTO.Request dto) {
		Long postId = dto.getPostId();
		UUID memberId = dto.getMemberId();

		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new NotFoundException(ErrorCode.POST_NOT_FOUND));

		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));


		PostLikeKey likeKey = PostLikeKey.builder()
			.post(postId)
			.member(memberId)
			.build();
		Optional<PostLike> postLike = postLikeRepository.findById(likeKey);
		boolean isLike = false;
		if(postLike.isPresent()){
			isLike = true;
		}

		boolean isMyPost = false;
		if(memberId == post.getMember().getId()){
			isMyPost = true;
		}

		PostDetailDTO.Post postDetail = PostDetailDTO.Post.builder()
			.albumImg(post.getPostAlbumImage())
			.content(post.getPostContent())
			.artist(post.getPostArtist())
			.title(post.getPostTitle())
			.nickname(member.getNickname())
			.likeCount(post.getLikeCount())
			.commentCount(post.getCommentCount())
			.isLike(isLike)
			.profileImg(member.getProfileImageUrl())
			.createdDate(post.getCreatedDate())
			.modifiedDate(post.getModifiedDate())
			.build();

		return PostDetailDTO.Response.builder()
			.post(postDetail)
			.build();
	}

	@Override
	public PostSaveDTO.Response savePost(PostSaveDTO.Request dto) {
		return null;
	}

	@Override
	public PostUpdateDTO.Response updatePost(PostUpdateDTO.Request dto) {
		return null;
	}

	@Override
	public PostDeleteDTO.Response deletePost(PostDeleteDTO.Request dto) {
		return null;
	}

	@Override
	public PostLikeDTO.Response likePost(PostLikeDTO.Request dto) {
		return null;
	}
}
