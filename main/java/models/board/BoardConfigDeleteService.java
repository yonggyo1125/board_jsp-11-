package models.board;

import commons.validator.Validator;

public class BoardConfigDeleteService {
	
	private BoardConfigDao boardConfigDao;
	private Validator<BoardConfig> validator;
	
	public BoardConfigDeleteService(BoardConfigDao boardConfigDao, Validator<BoardConfig> validator) {
		this.boardConfigDao = boardConfigDao;
		this.validator = validator;
	}
}
