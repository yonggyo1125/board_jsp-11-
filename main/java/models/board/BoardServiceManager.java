package models.board;

import commons.db.QueryExecutor;

public class BoardServiceManager {
	private static BoardServiceManager instance;
	
	private BoardServiceManager() {}
	
	public BoardConfigDao boardConfigDao() {
		return new BoardConfigDao(new QueryExecutor());
	}
	
	public BoardConfigSaveService getBoardConfigSaveService() {
		
		return new BoardConfigSaveService(boardConfigDao(), 
																new BoardConfigSaveValidator());
	}
	
	public BoardConfigDeleteService getBoardConfigDeleteService() {
		return new BoardConfigDeleteService(boardConfigDao(), null);
	}
	
	public BoardConfigListService getBoardConfigListService() {
		return new BoardConfigListService(boardConfigDao(), null);
	}
	
	public BoardConfigInfoService getBoardConfigInfoService() {
		return new BoardConfigInfoService(boardConfigDao());
	}
	
	public static BoardServiceManager getInstance() {
		if (instance == null) {
			instance = new BoardServiceManager();
		}
		
		return instance;
	}
}
