package sieum.community.exception;

public class CustomException extends RuntimeException {

	private final ErrorCode errorCode;

	protected CustomException(ErrorCode errorcode){
		this.errorCode = errorcode;
	}
}
