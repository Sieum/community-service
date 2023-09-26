package sieum.community.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class PostSaveDTO {

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Request{
		@JsonIgnore
		@JsonAlias(value = "member_id")
		private Long memberId;
		private String content;
		@JsonAlias(value = "album_img")
		private String albumImg;
		private String title;
		private String artist;
	}

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Response{
		@JsonAlias(value = "post_id")
		private Long postId;
		private boolean success;
	}


}
