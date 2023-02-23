package models.file;

import commons.validator.Validator;

public class FileDeleteService {
	private FileInfoDao fileInfoDao; 
	private Validator<FileInfo> validator;
	
	public FileDeleteService(FileInfoDao fileInfoDao, Validator<FileInfo> validator) {
		this.fileInfoDao = fileInfoDao;
		this.validator = validator;
	}
}
