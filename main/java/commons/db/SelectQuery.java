package commons.db;

import java.util.List;

public interface SelectQuery {
	<T, R> List<R> query(T t, String mapper); // 목록 조회 
	<R> List<R> query(String mapper);
	
	<T, R> R queryOne(T t, String mapper); // 단일 조회 
	<R> R queryOne(String mapper);
	
	< T> int count(T t, String mapper); // 갯수 체크 
}
