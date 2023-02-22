package models.board;

public class BoardConfigValidationException extends RuntimeException {
	public BoardConfigValidationException(String message) {
		super(message);
	}
}
