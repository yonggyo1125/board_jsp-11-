package models.board;

import java.util.List;

import commons.db.QueryExecutor;

public class BoardConfigDao {
	private QueryExecutor qe;
	
	public BoardConfigDao(QueryExecutor qe) {
		this.qe = qe;
	}
	
	// 게시글 설정 목록 
	public List<BoardConfig> gets(BoardConfig boardConfig) {
		List<BoardConfig> configs = qe.query(boardConfig, "BoardConfigMapper.configs");
		
		return configs;
	}
	
	public List<BoardConfig> gets() {
		List<BoardConfig> configs = qe.query("BoardConfigMapper.configs");
		return configs;
	}
	
	/**
	 *  게시판 설정  아이디로 조회 
	 *  
	 * @param id
	 * @return
	 */
	public BoardConfig get(String id) {
		BoardConfig params = new BoardConfig();
		params.setId(id);
		
		BoardConfig config = qe.queryOne(params, "BoardConfigMapper.config");
		
		return config;
	}
	
	/**
	 * 설정이 이미 있으면 수정, 없으면 추가 
	 * 
	 * @param boardConfig
	 * @return
	 */
	public boolean save(BoardConfig boardConfig) {
		int cnt = qe.queryOne(boardConfig, "BoardConfigMapper.count");
		
		int result = 0;
		if (cnt > 0) { // 설정이 이미 있으므로 수정
			result = qe.update(boardConfig, "BoardConfigMapper.update");
		} else { // 설정이 없으므로 추가 
			result = qe.insert(boardConfig, "BoardConfigMapper.insert");
		}
		
		return result > 0;
	}
	
	/**
	 * 아이디로 게시판 설정 삭제 
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(String id) {
		BoardConfig params = new BoardConfig();
		params.setId(id);
		int result = qe.delete(params, "BoardConfigMapper.delete");
		
		return result > 0;
	}
}
