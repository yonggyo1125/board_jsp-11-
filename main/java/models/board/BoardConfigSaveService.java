package models.board;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import commons.validator.Validator;

public class BoardConfigSaveService {
	private BoardConfigDao boardConfigDao;
	private Validator<BoardConfig> validator;
	
	public BoardConfigSaveService(BoardConfigDao boardConfigDao, Validator<BoardConfig> validator) {
		this.boardConfigDao = boardConfigDao;
		this.validator = validator;
	}
	
	public void save(HttpServletRequest request) {
		/**
		 * 1. 필수 데이터 체크 - id, boardNm
		 * 2. 기본값 부여, isUse - 0, useEditor - 1,
		 *     postsPerPage - 20 
		 *     pageRanges - 10
		 *     useComment - 0
		 * 3. DB 처리
		 */
		
		BoardConfig boardConfig = new BoardConfig();
		boardConfig.setId(request.getParameter("id"));
		boardConfig.setBoardNm(request.getParameter("boardNm"));
		String isUse = request.getParameter("isUse");
		boardConfig.setIsUse(isUse == null?0:Integer.parseInt(isUse));
		
		String useEditor = request.getParameter("useEditor");
		boardConfig.setUseEditor(useEditor == null? 0 : Integer.parseInt(useEditor));
		
		// basic, image, basic||image
		String[] attachFileTypes = request.getParameterValues("attachFileType");
		String attachFileType =  null;
		if (attachFileTypes != null) {
			attachFileType= Arrays.stream(attachFileTypes).collect(Collectors.joining("||"));
		}
		
		boardConfig.setAttachFileType(attachFileType);
		
		String postsPerPage = request.getParameter("postsPerPage");
		boardConfig.setPostsPerPage((postsPerPage == null || postsPerPage.isBlank())?0:Integer.parseInt(postsPerPage));
		
		String pageRanges = request.getParameter("pageRanges");
		boardConfig.setPageRanges((pageRanges == null || pageRanges.isBlank())?0:Integer.parseInt(pageRanges));
		
		String useComment = request.getParameter("useComment");
		boardConfig.setUseComment(useComment == null ? 0 : Integer.parseInt(useComment));
		
		boardConfig.setTopHtml(request.getParameter("topHtml"));
		boardConfig.setBottomHtml(request.getParameter("bottomHtml"));
		
		// 1. 필수 데이터 체크, 2. 기본값 부여
		if (validator != null) {
			validator.check(boardConfig);
		}
		
		// 3. DB 처리 
		boolean result = boardConfigDao.save(boardConfig);
		if (!result) {
			throw new BoardConfigException("설정 정보 저장에 실패하였습니다.");
		}
	}
	
}
