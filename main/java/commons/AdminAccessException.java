package commons;

public class AdminAccessException extends RuntimeException {
	public AdminAccessException() {
		super("접속권한이 없습니다.");
	}
}
