package commons.db;

public interface UpdateQuery {
	<T> int update(T t, String mapper);
}
