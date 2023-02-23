package models.file;

public class FileMaxCapacityException extends RuntimeException {
	public FileMaxCapacityException() {
		super("파일 업로드 최대 용량(...)을 초과하였습니다.");
	}
}
