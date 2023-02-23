package models.file;

public class NotAllowedFileTypeException extends RuntimeException {
	public NotAllowedFileTypeException() {
		super("허용되지 않은 파일 형식입니다.");
	}
}
