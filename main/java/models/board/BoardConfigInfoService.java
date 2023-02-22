package models.board;

import commons.validator.Validator;

public class BoardConfigInfoService {
	private BoardConfigDao boardConfigDao;
	
	public BoardConfigInfoService(BoardConfigDao boardConfigDao) {
		this.boardConfigDao = boardConfigDao;
	}
	
	/**
	 * 게시판 아이디로 설정 조회 
	 * 
	 * @param id
	 * @return
	 */
	public BoardConfig get(String id) {
		
		BoardConfig config = boardConfigDao.get(id);
		if (config == null) {
			throw new BoardConfigNotExistException();
		}
		
		return config;
	}
}
