package models.file;

import commons.PropertyLibrary;

public class FileMaxCapacityException extends RuntimeException {
	private static String totalMax;
	private static String fileMax;
	
	static {
		totalMax = PropertyLibrary.get("maxTotalFileSize");
		fileMax = PropertyLibrary.get("maxFileSize");
	}
	
	public FileMaxCapacityException() {
		super("파일 업로드 최대 용량(파일 " + fileMax + "mb(최대 " + totalMax + "mb)을 초과하였습니다.");
	}
}
