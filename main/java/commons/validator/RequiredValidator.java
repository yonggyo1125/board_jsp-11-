package commons.validator;

public interface RequiredValidator {
	/**
	 * 필수 항목 체크 
	 * @param value 
	 * @param e
	 */	
	default void checkRequired(String value, RuntimeException e) {
		if (value == null || value.isBlank()) {
			throw e;
		}
	}
	
	default String defaultValue(String value, String defaultValue) {
		if (value == null || value.isBlank())
			return defaultValue; // value 값이 없으면 기본값 반환 
		
		return value;
	}
}
