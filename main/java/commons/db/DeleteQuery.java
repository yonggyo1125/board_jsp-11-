package commons.db;

public interface DeleteQuery {
	<T> int delete(T t, String mapper); // DELETE 쿼리 
}
