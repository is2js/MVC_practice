package calculatorby2.domain;

import java.util.List;

import calculatorby2.utils.Util;

public class Token {
	private List<Integer> operands;
	private List<String> operators;

	private Token(String mathExression) {
		//convert
		this.operands = Util.extractOperands(mathExression.split(" "));
		this.operators = Util.extractOperators(mathExression.split(" "));

	}

	public static Token from(String mathExression) {
		checkValidate(mathExression);
		return new Token(mathExression);
	}

	private static void checkValidate(String mathExression) {
		checkNull(mathExression);
		// *Null -> 길이 -> (포맷) -> 범위 -> 기타
		checkValidLength(mathExression);
		checkValidOdd(mathExression);
	}

	private static void checkValidOdd(String mathExression) {
		if (!isOddNumber(mathExression)) {
			throw new IllegalArgumentException("공백포함해서 홀수개를 입력해주세요.");
		}
	}

	private static boolean isOddNumber(String mathExression) {
		return mathExression.length() % 2 != 0;
	}

	private static void checkValidLength(String mathExression) {
		if (!(mathExression.length() >= 3)) {
			throw new IllegalArgumentException("3글자 이상 입력");
		}
	}

	private static void checkNull(String mathExression) {
		// 문자열 null체크는 check 2개
		if (isEmpty(mathExression)) {
			throw new IllegalArgumentException("not a number");
		}
	}

	private static boolean isEmpty(String mathExression) {
		return mathExression == null || mathExression.trim().isEmpty();
	}

	public List<Integer> getOperands() {
		return operands;
	}

	public List<String> getOperators() {
		return operators;
	}
}
