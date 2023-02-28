CREATE TABLE boardData (
	id INT AUTO_INCREMENT COMMENT '게시글 등록번호', 
    boardId VARCHAR(35) NOT NULL COMMENT '게시판 ID',
    userNo INT DEFAULT 0 COMMENT '회원번호, 0 - 비회원',
	poster VARCHAR(35) NOT NULL COMMENT '작성자명',
    subject VARCHAR(255) NOT NULL COMMENT '게시글 제목',
    content LONGTEXT NOT NULL COMMENT '게시글 내용',
    hit INT DEFAULT 0 COMMENT '조회수',
    regDt DATETIME DEFAULT NOW(),
    modDt DATETIME,
    PRIMARY KEY(id)
);