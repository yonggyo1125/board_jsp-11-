package models.file;

import java.time.LocalDateTime;

public class FileInfo {
	private int id; // 파일 등록번호
	private String gid; // 그룹 ID
	private String fileName; // 원본 파일명
	private String fileType; // 파일 종류
	private LocalDateTime regDt; // 파일 업로드 일시 
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getGid() {
		return gid;
	}
	
	public void setGid(String gid) {
		this.gid = gid;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileType() {
		return fileType;
	}
	
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public LocalDateTime getRegDt() {
		return regDt;
	}
	
	public void setRegDt(LocalDateTime regDt) {
		this.regDt = regDt;
	}

	@Override
	public String toString() {
		return "FileInfo [id=" + id + ", gid=" + gid + ", fileName=" + fileName + ", fileType=" + fileType + ", regDt="
				+ regDt + "]";
	}
	
}
