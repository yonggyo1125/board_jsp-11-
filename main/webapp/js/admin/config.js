window.addEventListener("DOMContentLoaded", function() {
	CKEDITOR.replace("boardTopHtml");
	CKEDITOR.replace("boardBottomHtml");
	
	/** 상단, 하단 에디터 업로드 파일 목록  */
	updateFileList();
});

async function updateFileList() {
	const gid = document.querySelector("input[name='gid']").value;
	
	const topFiles = await koreait.fileManager.gets(`${gid}_topHtml`);
	const bottomFiles = await koreait.fileManager.gets(`${gid}_bottomHtml`);
	const files = [...topFiles, ...bottomFiles];
	
	const fileTpl = document.getElementById("fileTpl").innerHTML;
	const contextPathEl = document.getElementById("contextPath");
	const contextPath = contextPathEl?contextPathEl.value:"";
	const domParser = new DOMParser();
	const topEl = document.getElementById("uploaded_files_top");
	const bottomEl = document.getElementById("uploaded_files_bottom");
	for (const file of files) {
		let html = fileTpl;
		html = html.replace(/#downloadUrl#/g, `${contextPath}/file/download/${file.id}`)
						.replace(/#fileName#/g, file.fileName)
						.replace(/#id#/g, file.id);
		const dom = domParser.parseFromString(html, "text/html");
		const liEl = dom.querySelector("li");
		const removeEl = liEl.querySelector(".remove");
		removeEl.addEventListener("click", koreait.fileManager.delete);
		
		if (file.gid.indexOf("bottomHtml") != -1) { // 하단 
			bottomEl.appendChild(liEl);
		} else { // 상단
			topEl.appendChild(liEl);
		}
	}
}

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
	
	const uploadedFilesTop = document.getElementById("uploaded_files_top");
	const uploadedFilesBottom = document.getElementById("uploaded_files_bottom");
	const fileTpl = document.getElementById("fileTpl").innerHTML; 
	
	const domParser = new DOMParser();
	let topHtml = "", bottomHtml = "";
	for (const file of files) {
		const imgTag = `<img src='${contextPath}${file.uploadUrl}'>`;
		let html = fileTpl;
		html = html.replace(/#downloadUrl#/g, `${contextPath}/file/download/${file.id}`)
							.replace(/#fileName#/g, file.fileName)
							.replace(/#id#/g, file.id);
							
		const dom = domParser.parseFromString(html, "text/html");
		const liEl = dom.querySelector("li");
		const removeEl = liEl.querySelector(".remove"); // 파일 삭제 클릭시 삭제 처리 
		removeEl.addEventListener("click", koreait.fileManager.delete);
		
		
		if (file.gid.indexOf("bottomHtml") == -1) { // 상단 에디터
		 	topHtml += imgTag;
		 	uploadedFilesTop.appendChild(liEl);
		 	
		} else { // 하단 에디터 
			bottomHtml += imgTag;
			uploadedFilesBottom.appendChild(liEl);
		}
	} // endfor 
	
	if (topHtml) { // 상단 에디터에 추가 
		CKEDITOR.instances.boardTopHtml.insertHtml(topHtml);
	}
	
	if (bottomHtml) { // 하단 에디터에 추가 
		CKEDITOR.instances.boardBottomHtml.insertHtml(bottomHtml);
	}
	
}