package models.file;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import commons.exception.BadRequestException;
import commons.validator.RequiredValidator;

public class FileDeleteService implements RequiredValidator {
	private FileInfoDao fileInfoDao; 
	
	public FileDeleteService(FileInfoDao fileInfoDao) {
		this.fileInfoDao = fileInfoDao;
	}
	
	public void delete(HttpServletRequest request) {
		/**
		 * 요청값 id -> 개별 삭제(우선 먼저 처림)
		 * 요청값 gid -> 그룹 ID 삭제
		 * 
		 * 1. 필수 항목 체크 (id, gid 둘중 하나는 꼭 있어야 한다...)
		 * 		- id, gid 둘다 있는 경우 id로 삭제
		 * 
		 * 2. 파일조회(id), 파일 목록(gid) 조회 -> 형식을 통일화 id로 조회한 파일도 List 형태로 변환
		    2.5. 조회된 파일이 없는 경우 -> FileNotFoundException
		 * 3. 삭제 파일 중에서 회원이 업로드한 경우 권한 체크 
		 * 			-> 로그인 한 회원과 일치하는 경우만 삭제 가능
		 * 			-> 관리자인 경우는 무조건 삭제 가능
		 * 4. 파일 삭제
		 * 			-  DB에 있는 파일 기록 삭제
		 * 			- 실제 파일을 삭제
		 */
		
		//  1. 필수 항목 체크 S
		String _id = request.getParameter("id");
		String gid = request.getParameter("gid");
		checkRequired(_id, new BadRequestException());
		checkRequired(gid, new BadRequestException());
		//  1. 필수 항목 체크 E
		
		// 2. 파일조회(id), 파일 목록(gid) 조회 S 
		List<FileInfo> files = null;
		if (_id != null) {
			int id = Integer.parseInt(_id);
			FileInfo fileInfo = fileInfoDao.get(id);
			files = Arrays.asList(fileInfo);
		} else if (gid != null) {
			 files = fileInfoDao.gets(gid);
		}
		
		// 2. 파일조회(id), 파일 목록(gid) 조회 E
		
		//  2.5. 조회된 파일이 없는 경우 -> FileInfoNotFoundException
		if (files == null || files.size() == 0) {
			throw new FileInfoNotFoundException();
		}
		
	}
}
