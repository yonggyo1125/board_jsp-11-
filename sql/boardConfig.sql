CREATE TABLE boardConfig (
	id VARCHAR(30) PRIMARY KEY COMMENT '게시판 아이디',
    boardNm VARCHAR(60) NOT NULL COMMENT '게시판 이름',
    isUse TINYINT(1) DEFAULT 0 COMMENT '사용여부 1 - 사용, 0 - 미사용',
    useEditor TINYINT(1) DEFAULT 1 COMMENT '위지윅 에디터 사용 여부(1 - 사용)',
    attachFileType ENUM('basic', 'image') DEFAULT 'basic' COMMENT 'basic - 일반파일, image - 이미지',
    postsPerPage INT DEFAULT 20 COMMENT '한페이지당 게시글 수',
    pageRanges INT DEFAULT 10 COMMENT '페이지 구간 갯수',
    useComment TINYINT(1) DEFAULT 0 COMMENT '댓글 사용여부',
    topHtml LONGTEXT COMMENT '목록 상단 HTML',
    bottomHtml LONGTEXT COMMENT '목록 하단 HTML',
    regDt DATETIME DEFAULT NOW(),
    modDt DATETIME
);