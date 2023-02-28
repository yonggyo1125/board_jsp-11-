package models.file;

import java.util.List;

import commons.db.QueryExecutor;

public class FileInfoDao {
	
	private QueryExecutor qe;
	
	public FileInfoDao(QueryExecutor qe) {
		this.qe = qe;
	}
	
	/**
	 * 그룹 ID(gid)로 파일 목록 조회 
	 * 
	 * @param gid
	 * @return
	 */
	public List<FileInfo> gets(String gid) {
		FileInfo params = new FileInfo();
		params.setGid(gid);
		params.setDone(1);
		
		List<FileInfo> files = qe.query(params, "FileInfoMapper.files");
		
		return files;
	}
	
	/**
	 * 파일 아이디로 정보 조회
	 * 
	 * @param id
	 * @return
	 */
	public FileInfo get(int id) {
		FileInfo params = new FileInfo();
		params.setId(id);
		
		FileInfo file = qe.queryOne(params, "FileInfoMapper.file");
		
		return file;
	}
	
	/**
	 * 파일 정보 추가 
	 * 
	 * @param fileInfo
	 * @return 
	 */
	public boolean insert(FileInfo fileInfo) {
		int cnt = qe.insert(fileInfo, "FileInfoMapper.insert");
		
		return cnt > 0;
	}
	
	/**
	 * 파일 1개 삭제
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(int id) {
		FileInfo params = new FileInfo();
		params.setId(id);
		int cnt = qe.delete(params, "FileInfoMapper.delete");
		
		return cnt > 0;
	}
	
	/**
	 * 그룹 ID 파일 여러개 삭제 
	 * 
	 * @param gid
	 * @return
	 */
	public boolean delete(String gid) {
		FileInfo params = new FileInfo();
		params.setGid(gid);
		
		int cnt = qe.delete(params, "FileInfoMapper.deletes");
		
		return cnt > 0;
	}
	
	// 파일과 연관된 작업이 완료된 경우 완료처리 
	public void updateDone(String gid) {
		FileInfo params = new FileInfo();
		params.setGid(gid);
		
		qe.update(params, "FileInfoMapper.done");
	}
	
	public void updateDone(int id) {
		FileInfo params = new FileInfo();
		params.setId(id);
		
		qe.update(params, "FileInfoMapper.done");
	}
}
