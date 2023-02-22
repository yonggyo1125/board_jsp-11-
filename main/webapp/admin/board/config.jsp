<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@ taglib prefix="util"  tagdir="/WEB-INF/tags/utils" %>
<c:url var="action" value="/admin/board/config" />
<layout:admin>
	<div class='main_tit'>
		<c:if test="${empty boardConfig }">
			게시판 등록
		</c:if>
		<c:if test="${ !empty boardConfig }">
			[${boardConfig.boardNm}(${boardConfig.id})] 설정 수정
		</c:if>
	</div>
	
	<form name="saveFrm" method="post" action="${action}" target="ifrmProcess" autocomplete="off">
		<table class='table_cols'>
			<tr>
				<th>게시판 ID</th>
				<td>
					<c:if test="${empty boardConfig}">
						<input type="text" name="id">
					</c:if>
					<c:if test="${!empty boardConfig}">
						<input type="hidden" name="id" value="${boardConfig.id}">
						${boardConfig.id}
						<a href="<c:url value='/board/list/${boardConfig.id}' />" target="_blank">
							[게시판 미리보기]
						</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<th>게시판이름</th>
				<td>
					<input type="text" name="boardNm" value="${boardConfig.boardNm}">
				</td>			
			</tr>
			<tr>
				<th>사용여부</th>
				<td>
<input type="radio" name="isUse" value="1" id="isUse_1"${(boardConfig != null && boardConfig.isUse == 1) ? ' checked' : ''}>
					<label for="isUse_1">사용</label>
					<input type="radio" name="isUse" value="0" id="isUse_0"${(boardConfig != null && boardConfig.isUse == 1) ? '' : ' checked'}>
					<label for="isUse_0">미사용</label>	
				</td>
			</tr>
			<tr>
				<th>위지윅에디터</th>
				<td>
					<input type="radio" name="useEditor" value="1" id="useEditor_1"${(boardConfig != null && boardConfig.useEditor == 1) ? ' checked' : ''}>
					<label for="useEditor_1">사용</label>
					<input type="radio" name="useEditor" value="0" id="useEditor_0"${(boardConfig != null && boardConfig.useEditor == 1) ? '' : ' checked'}>
					<label for="useEditor_0">미사용</label>
				</td>
			</tr>
			<tr>
				<th>파일첨부</th>
				<td>
					<util:checkbox name="attachFileType" value="basic" label="일반파일" checkedValue="${boardConfig.attachFileType}" /> 
					<util:checkbox name="attachFileType" value="image" label="이미지" checkedValue="${boardConfig.attachFileType}" /> 
				</td>
			</tr>
			<tr>
				<th>한페이지 게시글 갯수</th>
				<td>
					<input type="number" name="postsPerPage" value="${boardConfig.postsPerPage}">
				</td>
			</tr>
			<tr>
				<th>페이지 구간 갯수</th>
				<td>
					<input type="number" name="pageRanges" value="${boardConfig.pageRanges}">
				</td>
			</tr>
			<tr>
				<th>댓글 사용</th>
				<td>
					<input type="radio" name="useComment" value="1" id="useComment_1"${(boardConfig != null && boardConfig.useComment == 1) ? ' checked' : ''}>
					<label for="useComment_1">사용</label>
					<input type="radio" name="useComment" value="0" id="useComment_0"${(boardConfig != null && boardConfig.useComment == 1) ? '' : ' checked'}>
					<label for="useComment_0">미사용</label>
				</td>
			</tr>
			<tr>
				<th>목록 상단</th>
				<td>
					<textarea id="boardTopHtml" name="topHtml">${boardConfig.topHtml}</textarea>
				</td>
			</tr>
			<tr>
				<th>목록 하단</th>
				<td>
					<textarea id="boardBottomHtml" name="bottomHtml">${boardConfig.bottomHtml}</textarea>
				</td>
			</tr>
		</table>
		<div class='btns'>
			<button type="reset">다시입력</button>
			<button type="submit">
				${boardConfig == null ? '등록하기' : '수정하기' }
			</button>
		</div>
	</form>
</layout:admin>