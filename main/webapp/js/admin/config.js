window.addEventListener("DOMContentLoaded", function() {
	CKEDITOR.replace("boardTopHtml");
	CKEDITOR.replace("boardBottomHtml");
});

// 파일 업로드 후 후속처리
function callbackFileUpload(files) {
	if (!files || files.length == 0) { // 파일 데이터가 있는 경우 
		return;
	}
	
	/**
	 *  1. 업로드된 이미지를 에디터에 추가 
	 *  2. 에디터 하단에 파일 목록 출력
	 *  
	 */
	const contextPathEl = document.getElementById("contextPath");
	let contextPath = contextPathEl ? contextPathEl.value : "";
	
	let topHtml = "", bottomHtml = "";
	for (const file of files) {
		const imgTag = `<img src='${contextPath}${file.uploadUrl}'>`;
		if (file.gid.indexOf("bottomHtml") == -1) { // 상단 에디터
		 	topHtml += imgTag;
		 	
		} else { // 하단 에디터 
			bottomHtml += imgTag;
		}
	} // endfor 
	
	if (topHtml) { // 상단 에디터에 추가 
		CKEDITOR.instances.boardTopHtml.insertHtml(topHtml);
	}
	
	if (bottomHtml) { // 하단 에디터에 추가 
		CKEDITOR.instances.boardBottomHtml.insertHtml(bottomHtml);
	}
	
}