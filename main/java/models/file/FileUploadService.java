package models.file;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import commons.validator.Validator;

public class FileUploadService {
	
	private FileInfoDao fileInfoDao;
	private Validator<FileInfo> validator;
	
	public FileUploadService(FileInfoDao fileInfoDao, Validator<FileInfo> validator) {
		this.fileInfoDao = fileInfoDao;
		this.validator = validator;
	}
	
	public List<FileInfo> upload(HttpServletRequest request) {
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
		 * 			- NotAllowedFileTypeException
		 * 
		 * 5. 파일이 O, DB에 파일 정보 기록(fileInfo)
		 * 6. 파일 등록(id)를 가지고 
		 * 			webapp/uploads/[0-9]/id(파일 등록 아이디) 저장 
		 * 7. 등록 성공한 파일만 업로드 한 파일 목록으로 추가 
		 * 8. 업로드 성공 파일 목록 반환 
		 * 9. 파일업로드 최종 실패 -> FileUploadException
		 */
	}
}
