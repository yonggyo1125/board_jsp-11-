package models.file;

public class FileListService {
	private FileInfoDao fileInfoDao;
	
	public FileListService(FileInfoDao fileInfoDao) {
		this.fileInfoDao = fileInfoDao;
	}
}
