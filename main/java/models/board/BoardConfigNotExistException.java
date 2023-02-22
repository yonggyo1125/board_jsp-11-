package models.board;

public class BoardConfigNotExistException extends RuntimeException {
	public BoardConfigNotExistException() {
		super("등록되지 않은 게시판입니다.");
	}
}
