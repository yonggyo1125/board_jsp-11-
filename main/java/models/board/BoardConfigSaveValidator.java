package models.board;

import commons.validator.RequiredValidator;
import commons.validator.Validator;

public class BoardConfigSaveValidator implements Validator<BoardConfig>, RequiredValidator{

	@Override
	public void check(BoardConfig boardConfig) {
		/**
		 *  1. 필수 데이터 체크 - id, boardNm
		 * 2. 기본값 부여, isUse 
		 *     postsPerPage - 20 
		 *     pageRanges - 10
		 *   
		 */
		
		// 1. 필수 데이터 체크 
		checkRequired(boardConfig.getId(), new BoardConfigValidationException("게시판 ID를 입력하세요."));
		checkRequired(boardConfig.getBoardNm(), new BoardConfigValidationException("게시판명을 입력하세요."));
		
		// 2. 기본값 부여
		int postsPerPage = boardConfig.getPostsPerPage();
		int pageRanges = boardConfig.getPageRanges();
		if (postsPerPage <= 0) boardConfig.setPostsPerPage(20);
		if (pageRanges <= 0) boardConfig.setPageRanges(10);
	}

}
