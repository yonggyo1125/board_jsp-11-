package commons.db;

public interface InsertQuery {
	<T> int insert(T t, String mapper); // INSERT 쿼리 
}
