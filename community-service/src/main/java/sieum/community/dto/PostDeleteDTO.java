package sieum.community.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class PostDeleteDTO {
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Request{
		@JsonIgnore
		@JsonAlias(value = "member_id")
		private Long memberId;
		@JsonIgnore
		@JsonAlias(value = "post_id")
		private Long postId;
	}

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Response{
		private boolean success;
	}
}
