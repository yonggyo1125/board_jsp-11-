package models.file;

import commons.validator.Validator;

public class FileUploadService {
	
	private FileInfoDao fileInfoDao;
	private Validator<FileInfo> validator;
	
	public FileUploadService(FileInfoDao fileInfoDao, Validator<FileInfo> validator) {
		this.fileInfoDao = fileInfoDao;
		this.validator = validator;
	}
}
