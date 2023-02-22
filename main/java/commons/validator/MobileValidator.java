package commons.validator;

public interface MobileValidator {
	/**
	 * 휴대전화번호 유효성 검사 
	 * @param mobile
	 * @return
	 */
	default boolean mobileCheck(String mobile) {
		/**
		 * 010-3481-2101
		 * 010  3481  2101
		 * 010.3481.2101
		 * 1. 휴대전화번호 -> 숫자만 남김 
		 * 2. 패턴 만들기 
		 * 	    010 3481 2101
		 *      011,6  123 2101
		 * 3. 체크
		 */
		  mobile = mobile.replaceAll("\\D", "");  
		  String pattern = "01[016]\\d{3,4}\\d{4}";
		
		  return mobile.matches(pattern);
	}
}
