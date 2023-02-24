CREATE TABLE fileInfo (
	id INT AUTO_INCREMENT COMMENT '서버 업로드 파일명',
    gid VARCHAR(45) NOT NULL COMMENT '그룹 ID',
    fileName VARCHAR(100) NOT NULL COMMENT '원본 파일명',
    fileType VARCHAR(50) NOT NULL COMMENT '파일 종류',
    regDt DATETIME DEFAULT NOW() COMMENT '파일 업로드 일시',
    PRIMARY KEY(id)
);

ALTER TABLE fileInfo ADD userNo INT AFTER id;

ALTER TABLE fileInfo ADD FOREIGN KEY(userNo)
	REFERENCES member(userNo);
	
	
ALTER TABLE fileInfo DROP CONSTRAINT fileinfo_ibfk_1;