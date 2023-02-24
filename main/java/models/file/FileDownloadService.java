package models.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;import java.text.Bidi;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import commons.exception.BadRequestException;

public class FileDownloadService {
	private FileInfoDao fileInfoDao;
	
	public FileDownloadService(FileInfoDao fileInfoDao) {
		this.fileInfoDao = fileInfoDao;
	}
	// /file/download/파일번호
	public void download(HttpServletRequest request, HttpServletResponse response) {
		/**
		 *  파일 id -> 정보 조회 -> 다운로드
		 * 1. 필수 항목체크  - 파일 등록번호(id)
		 * 			- /file/download/파일번호 ->파일 번호 추출 
		 * 			- 파일 등록번호 값 체크 
		 * 2. DB에서 파일 정보 조회
		 * 3. 실제 파일이 정보대로 있는지 체크
		 * 4. 응답 헤더 작성(다운로드 위한)
		 * 5. body쪽에 파일 데이터를 읽어서 출력
		 * 			- ServletOutputStream -> HttpServletResponse:getOutStream()
		 */
		// + -> 최대 매칭 * -> 최소 매칭
		// 1. 필수 항목체크  - 파일 등록번호(id) S
		int id = 0;
		String uri = request.getRequestURI();
		String pattern = "file/download/([^\\?]*)";
		Pattern compile = Pattern.compile(pattern);
		Matcher matcher = compile.matcher(uri);
		if (matcher.find()) {
			id = Integer.parseInt(matcher.group(1));
		}
		if (id == 0) {
			throw new BadRequestException();
		}
		// 1. 필수 항목체크  - 파일 등록번호(id) E
		
		// 2. DB에서 파일 정보 조회
		FileInfo fileInfo = fileInfoDao.get(id);
		if (fileInfo == null) { // 파일 조회가 되지 않은 경우 
			throw new FileInfoNotFoundException();
		}
		
		// 3. 실제 파일이 정보대로 있는지 체크
		String filePath = fileInfo.getFilePath(request);
		File file = new File(filePath);
		if (!file.exists()) { // 파일이 없는 경우 
			throw new FileInfoNotFoundException();
		}
		
		// 4. 응답 헤더 작성(다운로드 위한)
		String fileName = fileInfo.getFileName();
		try {
			fileName = new String(fileName.getBytes(), "ISO8859_1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// 윈도우즈 인경우는 한글 인코딩이 2바이트 CPC949(EUC-KR) 
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		response.setHeader("Content-Type", "application/octet-stream");
		response.setIntHeader("Expires", 0);
		response.setHeader("Cache-Control", "must-revalidate");
		response.setHeader("Pragma", "public");
		response.setHeader("Content-Length", ""+file.length());
		
		// 5. body쪽에 파일 데이터를 읽어서 출력
		try (FileInputStream fis = new FileInputStream(file);
			   BufferedInputStream bis = new BufferedInputStream(fis)) {
			
			OutputStream os = response.getOutputStream(); 
			int i = 0;
			while ((i = bis.read()) != -1) {
				os.write(i);
			}
			os.flush(); // 버퍼 비우기 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
