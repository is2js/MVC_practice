package baseball2Youngyooon.domain;

import baseball2Youngyooon.utils.Util;

public class Token {
	private int number;
	//6. 원시타입을 포장해서 class에 가두면, get으로 직접못꺼내보고 내부에서 처리된 결과값만 보내야한다.

	private Token(String number) {
		//1. private 생성자에선 convert(문->숫) 및 필요시 숫자list -> stream으로 타객체   로 생성작업이 들어간다.
		// -> from스태틱 객체생성메서드에서 이미 검증이 끝난 String이 오기 때문에, convert(문->숫, 숫->객체)만 한다.
		this.number = Util.ConvertStrToInt(number);
	}

	//2. of or from 스태틱 메소드로  String 검증만 하고 -> 생성자호출 한다.
	public static Token from(String number) {
		checkValidation(number);
		return new Token(number);
	}

	private static void checkValidation(String number) {
		checkValidLength(number);
		checkValidFormat(number);
		checkValidRange(number);

	}

	private static void checkValidRange(String number) {
		if (!isValidRange(number)) {
			throw new IllegalArgumentException("1 혹은 2를 입력해주세요.");
		}
	}

	private static boolean isValidRange(String number) {
		return number.equals("1") || number.equals("2");
	}

	private static void checkValidFormat(String number) {
		char character = number.toCharArray()[0];
		if (isDigit(character)) {
			throw new IllegalArgumentException("숫자를 입력해주세요");
		}
	}

	private static boolean isDigit(char c) {
		return !(Character.isDigit(c));
	}

	private static void checkValidLength(String number) {
		if (!isValidLength(number)) {
			throw new IllegalArgumentException("1글자만 입력해주세요");
		}
	}

	private static boolean isValidLength(String number) {
		return number.length() == 1;
	}

	public boolean isRestart() {
		return this.number == 1;
	}
}
