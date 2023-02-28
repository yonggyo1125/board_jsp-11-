package models.file;

import java.io.File;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

public class FileInfo {
	private int id; // 파일 등록번호
	private int userNo; // 회원번호
	private String gid; // 그룹 ID
	private String fileName; // 원본 파일명
	private String fileType; // 파일 종류
	private int done; // 0 - 미완료, 1 - 완료
	private LocalDateTime regDt; // 파일 업로드 일시 
	
	private String uploadUrl; // 파일 업로드 URL;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
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
	
	
	public String getUploadUrl() {
		int folder = id % 10;
		String url = "/uploads/" + folder + "/" + id;
		return url;
	}

	public int getDone() {
		return done;
	}

	public void setDone(int done) {
		this.done = done;
	}

	/**
	 * 실제 업로드된 파일 경로 
	 * 
	 * @param request
	 * @return
	 */
	public String getFilePath(HttpServletRequest request) {
		int folder = id % 10;
		
		StringBuffer sb = new StringBuffer(150);
		sb.append(request.getServletContext().getRealPath("."));
		sb.append(File.separator);
		sb.append("uploads");
		sb.append(File.separator);
		sb.append(folder);
		sb.append(File.separator);
		sb.append(id);
		
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return "FileInfo [id=" + id + ", userNo=" + userNo + ", gid=" + gid + ", fileName=" + fileName + ", fileType="
				+ fileType + ", regDt=" + regDt + "]";
	}
	
}
