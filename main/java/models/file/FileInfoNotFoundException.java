package models.file;

public class FileInfoNotFoundException extends RuntimeException {
	public FileInfoNotFoundException() {
		super("조회된 파일 정보가 없습니다.");
	}
}
