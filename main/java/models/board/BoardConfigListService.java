package models.board;

import commons.validator.Validator;

public class BoardConfigListService {
	
	private BoardConfigDao boardConfigDao;
	private Validator<BoardConfig> validator;
	
	public BoardConfigListService(BoardConfigDao boardConfigDao, Validator<BoardConfig> validator) {
		this.boardConfigDao = boardConfigDao;
		this.validator = validator;
	}
}
