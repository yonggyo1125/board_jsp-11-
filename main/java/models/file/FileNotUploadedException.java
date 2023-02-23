package models.file;

public class FileNotUploadedException extends RuntimeException {
	public FileNotUploadedException() {
		super("파일을 업로드 하세요.");
	}
}
