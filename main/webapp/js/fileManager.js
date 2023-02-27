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
			
			const gid = dataset.gid;
			const formData = new FormData();
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
					console.log(xhr.responseText);
				}	
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
	gets(gid) {},
	/**
	 * 파일 목록 삭제
	 * 
	 */
	deletes(gid) {},
	/**
	 * 파일 아이디로 삭제
	 */
	delete(id) {}
};


/**
 * fileUpload 클래스가 있는 경우는 파일 업로드 처리 
 */
window.addEventListener("DOMContentLoaded", function() {
	const fileUploads = document.getElementsByClassName("fileUpload");
	for (const el of fileUploads) {
		el.addEventListener("change", koreait.fileManager.upload);
	}
});






