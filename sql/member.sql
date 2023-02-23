CREATE TABLE `member` (
   `userNo` int NOT NULL AUTO_INCREMENT,
   `userId` varchar(40) NOT NULL,
   `userPw` varchar(65) NOT NULL,
   `userNm` varchar(40) NOT NULL,
   `mobile` varchar(11) DEFAULT NULL,
   `userType` enum('MEMBER','ADMIN') DEFAULT 'MEMBER',
   `regDt` datetime DEFAULT CURRENT_TIMESTAMP,
   `modDt` datetime DEFAULT NULL,
   PRIMARY KEY (`userNo`),
   UNIQUE KEY `userId_UNIQUE` (`userId`)
 ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci