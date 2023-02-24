package models.file;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import commons.exception.BadRequestException;

public class FileListService {
	private FileInfoDao fileInfoDao;
	
	public FileListService(FileInfoDao fileInfoDao) {
		this.fileInfoDao = fileInfoDao;
	}
	
	public List<FileInfo> gets(HttpServletRequest request) {
		/**
		 * /file/list/그룹ID(gid)
		 *
		 * 1. 필수 항목 체크 (gid)
		 * 	2. DB 조회 
		 * 3. 파일 목록 반환
		 */
		
		// 1. 필수 항목 체크 (gid)
		String gid = null;
		String uri = request.getRequestURI();
		String pattern = "file/list/([^\\?]*)";
		Pattern compile = Pattern.compile(pattern);
		Matcher matcher = compile.matcher(uri);
		if (matcher.find()) {
			gid = matcher.group(1);
		}
		
		if (gid == null) {
			throw new BadRequestException();
		}
		
		// 2. DB 조회
		List<FileInfo> files = fileInfoDao.gets(gid);
		
		return files;
	}
}
