package models.board;

import java.time.LocalDateTime;

public class BoardData {
	private int id; // 게시글 번호
	private String gid; // 그룹 ID 
	private String boardId; // 게시판 ID
	private int userNo; // 회원번호 
	private String userId; // 회원 ID 
	private String userNm; // 회원명
	private String poster; // 작성자명
	private String subject; // 글제목
	private String content; // 글 내용 
	private int hit; // 글 조회수
	private LocalDateTime regDt; // 작성일시 
	private LocalDateTime modDt; // 수정일시 
	
	private int offset; // LIMIT 에서 레코드 시작위치 
	private int limit; // LIMIT에서 추출할 레코드 갯수  LIMIT offset 번호, limit 번호
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getGid() {
		return gid;
	}
	
	public void setGid(String gid) {
		this.gid = gid;
	}
	
	public String getBoardId() {
		return boardId;
	}
	
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	
	public int getUserNo() {
		return userNo;
	}
	
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserNm() {
		return userNm;
	}
	
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	
	public String getPoster() {
		return poster;
	}
	
	public void setPoster(String poster) {
		this.poster = poster;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getHit() {
		return hit;
	}
	
	public void setHit(int hit) {
		this.hit = hit;
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
	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		return "BoardData [id=" + id + ", gid=" + gid + ", boardId=" + boardId + ", userNo=" + userNo + ", userId="
				+ userId + ", userNm=" + userNm + ", poster=" + poster + ", subject=" + subject + ", content=" + content
				+ ", hit=" + hit + ", regDt=" + regDt + ", modDt=" + modDt + "]";
	}
}
