package models.file;

import commons.db.QueryExecutor;

public class FileServiceManager {
	private static FileServiceManager instance;
	
	private FileServiceManager() {}
	
	public FileInfoDao fileInfoDao() {
		return new FileInfoDao(new QueryExecutor());
	}
	
	public FileUploadService getFileUploadService() {
		return new FileUploadService(fileInfoDao());
	}
	
	public FileDeleteService getFileDeleteService() {
		return new FileDeleteService(fileInfoDao());
	}
	
	public FileListService getFileListService() {
		return new FileListService(fileInfoDao());
	}
	
	public FileDownloadService getFileDownloadService() {
		return new FileDownloadService(fileInfoDao());
	}
	
	public static FileServiceManager getInstance() {
		if (instance == null) {
			instance = new FileServiceManager(); 
		}
		
		return instance;
	}
}
