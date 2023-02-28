package models.board;

import commons.db.QueryExecutor;
import models.file.FileInfoDao;

public class BoardServiceManager {
	private static BoardServiceManager instance;
	
	private BoardServiceManager() {}
	
	public BoardConfigDao boardConfigDao() {
		return new BoardConfigDao(new QueryExecutor());
	}
	
	public FileInfoDao fileInfoDao() {
		return new FileInfoDao(new QueryExecutor());
	}
	
	public BoardConfigSaveService getBoardConfigSaveService() {
		
		BoardConfigSaveService service = new BoardConfigSaveService(boardConfigDao(), 
																new BoardConfigSaveValidator());
		service.setFileInfoDao(fileInfoDao());
		
		return service;
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
