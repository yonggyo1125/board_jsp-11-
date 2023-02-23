package models.file;

public class FileDownloadService {
	private FileInfoDao fileInfoDao;
	
	public FileDownloadService(FileInfoDao fileInfoDao) {
		this.fileInfoDao = fileInfoDao;
	}
}
