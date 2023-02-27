var koreait = koreait || {};
koreait.fileManager = {
	/**
	 * 파일 업로드 처리 
	 * 
	 */
	upload(e) {
		try {
			const files = e.target.files;
			const el = e.currentTarget;
			const dataset = el.dataset; // data-속성 		
			if (files.length == 0) { // 파일을 선택 X, 잘못된 방식으로 호출
				throw new Error("파일을 업로드 하세요.");
			}
			
			const formData = new FormData();
			
			const gid = dataset.gid;
			const imageOnly = dataset.imageOnly?true:false;
			// 이미지 형식만 허용 체크 
			if (imageOnly) {
				for(const file of  files) {
					if (file.type.indexOf("image") == -1) { //  이미지 이외의 다른 파일이 첨부된 경우 
						throw new Error("이미지 형식만 업로드 하세요.");
					}
				}
				
				formData.append("fileType", "image");
			}
			
			if (gid) {
				formData.append("gid", gid);
			}
			
			for (const file of files) {
				formData.append("file", file);
			}
			
			let contextPath = "";
			const contextPathEl = document.getElementById("contextPath");
			if (contextPathEl) { // 컨텍스트 경로가 있는 경우
				contextPath = contextPathEl.value;
			}
			const url = `${contextPath}/file/upload`;
			
			const xhr = new XMLHttpRequest();
			xhr.open("POST", url);
			xhr.send(formData);
			
			xhr.onreadystatechange = function() {
				if (xhr.status = 200 && xhr.readyState == XMLHttpRequest.DONE) {
					// 요청 성공시 
					const result = JSON.parse(xhr.responseText); // JSON 문자열(JSP 서버) -> JavaScript 객체로 변환
					if (!result.success) { // 파일 전송 실패 한경우 
						alert(result.message);
						return;
					}
					
					// 파일 전송 성공 
					const data = result.data;
					// callbackFileUpload 함수가 정의 되어 있는 경우는 이 함수를 호출, data를 넘겨준다.
					if (typeof callbackFileUpload == 'function') { 
						callbackFileUpload(data);
					}
					
					// 파일 업로드 태그 초기화
					el.value = ""; // 선택 해제 
				}	
			};
			
			xhr.onerror = function(err) {
				console.err(err);
			};
			
		} catch (err) {
			alert(err.message);
			console.error(err);
		}
	},
	/**
	 * 목록 조회 
	 * 
	 */
	gets(gid) {
		if (!gid) {
			return;
		}
		
		const contextPathEl = document.getElementById("contextPath");
		const contextPath = contextPathEl ? contextPathEl.value : "";
		const url = `${contextPath}/file/list/${gid}`;
		
		return new Promise(function(resolve, reject) {
			const xhr = new XMLHttpRequest();
			xhr.open("GET", url);
			xhr.send(null);
			
			xhr.onreadystatechange = function() {
				if (xhr.status == 200 && xhr.readyState == XMLHttpRequest.DONE) {
					const result = JSON.parse(xhr.responseText);
					if (!result.success) { // 실패 
						reject(result);
						return;
					}
					
					resolve(result.data); // 성공 
				}	
			};
			
			xhr.onerror = function(err) {
				reject(err);	
			};
		});
		
	},
	/**
	 * 파일 목록 삭제
	 * 
	 */
	deletes(gid) {
		if (!gid) {
			return;
		}
		
		const contextPathEl = document.getElementById("contextPath");
		const contextPath = contextPathEl ? contextPathEl.value : "";
		const url = `${contextPath}/file/delete?gid=${gid}`;
		const xhr = new XMLHttpRequest();
		xhr.open("GET", url);
		xhr.onreadystatechange = function() {
			if (xhr.status == 200 && xhr.readyState == XMLHttpRequest.DONE) {
				const data = JSON.parse(xhr.responseText);
				if (!data.success) {
					alert(data.message);
					return;
				}
				
				if (typeof callbackFileDelete == 'function') {
					callbackFileDelete(data);
				}
			}	
		};
	},
	/**
	 * 파일 아이디로 삭제
	 * 
	 */
	delete(e) {
				
		const el = e.currentTarget;
		const id = el.dataset.id;
		try {
			if (!id) {
				throw new Error("잘못된 접근입니다.");
			}
			
			if (!confirm("정말 삭제하시겠습니까?")) {
				return;
			}
				
			const contextPathEl = document.getElementById("contextPath");
			const contextPath = contextPathEl ? contextPathEl.value : "";
			const url = `${contextPath}/file/delete?id=${id}`;
			
			const xhr = new XMLHttpRequest();
			xhr.open("GET", url);
			xhr.send(null); // body 출력 데이터 
			
			xhr.onreadystatechange = function() {
				if (xhr.status == 200 && xhr.readyState == XMLHttpRequest.DONE) {
					const data = JSON.parse(xhr.responseText);
					if (!data.success) {
						alert(data.message);
						return;
					}
					
					// 삭제 완료 -> 파일 목록 HTML 삭제
					const liEl = el.parentElement; 
					liEl.parentElement.removeChild(liEl);
				}	
			};
			
		} catch (err) {
			alert(err.message);
			console.error(err);
		}
	}
};



window.addEventListener("DOMContentLoaded", function() {
	
	/**
 	* fileUpload 클래스가 있는 경우는 파일 업로드 처리 
 	*/
	const fileUploads = document.getElementsByClassName("fileUpload");
	for (const el of fileUploads) {
		el.addEventListener("change", koreait.fileManager.upload);
	}
	
	/**
	 * fileDelete 클래스가 있는 경우 파일 삭제 처리 
	 * 
	 */
	const fileDeletes = document.getElementsByClassName("fileDelete");
	for (const el of fileDeletes) {
		el.addEventListener("click", koreait.fileManager.delete);
	}
});






