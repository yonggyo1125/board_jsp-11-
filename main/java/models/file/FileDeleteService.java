package models.file;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import commons.MemberLibrary;
import commons.exception.BadRequestException;
import models.member.Member;

public class FileDeleteService {
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
		    2.5. 조회된 파일이 없는 경우 -> FileInfoNotFoundException
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
		
		// 둘다 없는 경우는 예외 발생
		if ((_id == null || _id.isBlank()) && (gid == null || gid.isBlank())) {
			throw new BadRequestException();
		}
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
		
		// 3. 삭제 파일 중에서 회원이 업로드한 경우 권한 체크 - 사용자와 관리자 
		for (FileInfo file : files) {
			int userNo = file.getUserNo();
			
			// 회원이 올린 파일이고 현재 로그인한 회원이 관리자가 아닐때
			if (userNo > 0 && !MemberLibrary.isAdmin(request)) {
				Member member = MemberLibrary.getLoginMember(request);
				if (member == null || member.getUserNo() != userNo) {
					throw new BadRequestException("본인이 업로드한 파일만 삭제가능합니다.");
				}
			}
		}
		
		// 4. 파일 삭제 - 파일 DB 삭제, 실제 파일 삭제 
		for(FileInfo file : files) {
			fileInfoDao.delete(file.getId()); // DB 삭제 
			
			String path = file.getFilePath(request); // 실제 업로드된 파일 경로
			File uploadFile = new File(path);
			if (uploadFile.exists()) { // 파일이 있는 경우 -> 삭제
				uploadFile.delete();
			}
		}
	}
}
