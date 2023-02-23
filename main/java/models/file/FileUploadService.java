package models.file;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import commons.PropertyLibrary;
import commons.validator.Validator;

public class FileUploadService {
	
	private FileInfoDao fileInfoDao;
	private Validator<FileInfo> validator;
	
	public FileUploadService(FileInfoDao fileInfoDao, Validator<FileInfo> validator) {
		this.fileInfoDao = fileInfoDao;
		this.validator = validator;
	}
	
	public List<FileInfo> upload(HttpServletRequest request) throws FileUploadException, UnsupportedEncodingException {
		/**
		 * 1. 요청 데이터(Multipart body) 분리 
		 * 		 List<FileItem> ServletFileUpload :: parseRequest
		 * 			FileItem 
		 * 					.isFormField()
		 * 2.업로드한 파일이 있는지 체크 
		 * 		   -  파일만 목록으로 추가
		 * 	       - 파일 목록이 없으면 X - FileNotUploadedException 
		 * 
		 * 3. 업로드한 파일의 최대 용량(설정) - FileMaxCapacityException
		 * 4. 파일 형식을 제한한 경우 - 이미지, 파일 종류 체크 
		 * 			- fileType  : contentType : image/png,  fileType 양식 데이터 -> 체크
		 * 			- NotAllowedFileTypeException
		 * 
		 * 5. 파일이 O, DB에 파일 정보 기록(fileInfo)
		 * 6. 파일 등록(id)를 가지고 
		 * 			webapp/uploads/[0-9]/id(파일 등록 아이디) 저장 
		 * 7. 등록 성공한 파일만 업로드 한 파일 목록으로 추가 
		 * 8. 업로드 성공 파일 목록 반환 
		 * 9. 파일업로드 최종 실패 -> FileUploadException
		 */
		String maxTotalFileSize = PropertyLibrary.get("maxTotalFileSize");
		String maxFileSize = PropertyLibrary.get("maxFileSize");
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// 파일 사이즈 제한 S
		if (maxTotalFileSize != null && !maxTotalFileSize.isBlank()) { 
			upload.setSizeMax(1024 * 1024 * Integer.parseInt(maxTotalFileSize));
		}
		
		if (maxFileSize != null && !maxFileSize.isBlank()) {
			upload.setFileSizeMax(1024 * 1024 * Integer.parseInt(maxFileSize));
		}
		// 파일 사이즈 제한 E
		
		// 1. 요청 데이터(Multipart body) 분리 
		List<FileItem> items = upload.parseRequest(request);
		
		
		// 2.업로드한 파일이 있는지 체크 S
		List<FileItem> uploadedFiles = new ArrayList<>(); // 업로드로 유입된 파일
		Map<String, String> requestData = new HashMap<>(); // 일반 양식 데이터 
		for (FileItem item : items) {
			if (item.isFormField()) { // 일반 양식 데이터
				String key = item.getFieldName();
				String value = item.getString("UTF-8");
				requestData.put(key, value);
			} else { // 파일 
				uploadedFiles.add(item);
			}
		}
		
		if (uploadedFiles.size() == 0) { // 업로드한 파일이 없는 경우 
			throw new FileNotUploadedException();
		}
		// 2.업로드한 파일이 있는지 체크 E
		
		// 4. 파일 형식을 제한한 경우 S // image  -> image/*
		String fileType = requestData.get("fileType"); 
		if (fileType != null) {
			for (FileItem item : items) {
				String type = item.getContentType();
				if (type.indexOf(fileType) == -1) { // fileType에 해당하지 않는 파일이 포함된 경우 
					throw new NotAllowedFileTypeException();
				}
			}
		}
		
		// 4. 파일 형식을 제한한 경우 E
		String gid = requestData.get("gid");
		if (gid == null) gid = "" + System.currentTimeMillis(); // 그룹ID 없으면 임의의 수
		for (FileItem item : items) {
			// 5. 파일이 O, DB에 파일 정보 기록(fileInfo) S
			String fileName = item.getName();
			fileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1);
			FileInfo fileInfo = new FileInfo();
			fileInfo.setFileName(fileName);
			fileInfo.setFileType(item.getContentType());
			fileInfo.setGid(gid);
			
			boolean result = fileInfoDao.insert(fileInfo);
			if (!result) { // DB 추가 실패한 경우는 파일 처리 건너뛰기 
				continue;
			}
			// 5. 파일이 O, DB에 파일 정보 기록(fileInfo) E
		}
	}
}
