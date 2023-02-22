package models.board;

import java.time.LocalDateTime;

/**
 * 게시판 설정 DTO
 *
 */
public class BoardConfig {
	private String id;  // 게시판 ID
	private String boardNm; // 게시판 이름
	private int isUse; // 사용 여부
	private int useEditor; // 위지윅 에디터 사용여부
	private String attachFileType; // basic - 일반파일 첨부, image - 이미지 파일 첨부
	private int postsPerPage; // 한페이지당 게시글 갯수
	private int pageRanges; // 페이지 구간 갯수
	private int useComment; // 댓글 사용 여부 
	private String topHtml; // 게시판 목록 상단 HTML
	private String bottomHtml; // 게시판 목록 하단 HTML
	private LocalDateTime regDt;
	private LocalDateTime modDt;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getBoardNm() {
		return boardNm;
	}
	
	public void setBoardNm(String boardNm) {
		this.boardNm = boardNm;
	}
	
	public int getIsUse() {
		return isUse;
	}
	
	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}
	
	public int getUseEditor() {
		return useEditor;
	}
	
	public void setUseEditor(int useEditor) {
		this.useEditor = useEditor;
	}
	
	public String getAttachFileType() {
		return attachFileType;
	}
	
	public void setAttachFileType(String attachFileType) {
		this.attachFileType = attachFileType;
	}
	
	public int getPostsPerPage() {
		return postsPerPage;
	}
	
	public void setPostsPerPage(int postsPerPage) {
		this.postsPerPage = postsPerPage;
	}
	
	public int getPageRanges() {
		return pageRanges;
	}
	
	public void setPageRanges(int pageRanges) {
		this.pageRanges = pageRanges;
	}
	
	public int getUseComment() {
		return useComment;
	}
	
	public void setUseComment(int useComment) {
		this.useComment = useComment;
	}
	
	public String getTopHtml() {
		return topHtml;
	}
	
	public void setTopHtml(String topHtml) {
		this.topHtml = topHtml;
	}
	
	public String getBottomHtml() {
		return bottomHtml;
	}
	
	public void setBottomHtml(String bottomHtml) {
		this.bottomHtml = bottomHtml;
	}
	
	public LocalDateTime getRegDt() {
		return regDt;
	}
	
	public void setRegDt(LocalDateTime regDt) {
		this.regDt = regDt;
	}
	
	public LocalDateTime getModDt() {
		return modDt;
	}
	
	public void setModDt(LocalDateTime modDt) {
		this.modDt = modDt;
	}

	@Override
	public String toString() {
		return "BoardConfig [id=" + id + ", boardNm=" + boardNm + ", isUse=" + isUse + ", useEditor=" + useEditor
				+ ", attachFileType=" + attachFileType + ", postsPerPage=" + postsPerPage + ", pageRanges=" + pageRanges
				+ ", useComment=" + useComment + ", topHtml=" + topHtml + ", bottomHtml=" + bottomHtml + ", regDt="
				+ regDt + ", modDt=" + modDt + "]";
	}
}
