package calculatorby1regex.domain;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import calculatorby1regex.utils.Util;

public class MathExpression {
	private List<Integer> numbers;

	public MathExpression(String[] splitedStringNumbers) {
		// 생성자에서는 검증된 것을 받아서..->  util -> convert 위주로
		List<Integer> integerNumbers = Util.convertIntList(splitedStringNumbers);
		this.numbers = integerNumbers;
	}

	public static MathExpression of(String mathExression) {
		// checkValidation(mathExression);
		// validation보다.. 일단 split부터 해야한다?
		String[] splitedStringNumber = splitExpression(mathExression);
		checkNegativeNumber(splitedStringNumber);
		return new MathExpression(splitedStringNumber);
	}

	private static void checkNegativeNumber(String[] splitedStringNumber) {
		if (hasAnyNegative(splitedStringNumber)) {
			throw new RuntimeException();
		}
	}

	private static boolean hasAnyNegative(String[] splitedStringNumber) {
		return Arrays.stream(splitedStringNumber).mapToInt(Integer::parseInt).boxed().anyMatch(i -> i < 0);
	}

	private static String[] splitExpression(String mathExression) {
		// 1. 커스텀구분자인경우 -> 구분자와, 식을 matcher group(1), (2)로 나눠서 받아서 처리한다.
		Matcher matcher = Pattern.compile("//(.)\n(.*)").matcher(mathExression.trim());
		System.out.println(matcher.matches());
		if (matcher.matches()) {
			System.out.println("정규표현식 걸렸다.");
			String seperator = matcher.group(1);
			String expression = matcher.group(2);
			// 문자열 숫자배열 convert는.. 생성자에서 해주자.
			return expression.split(seperator);
		}

		Matcher m = Pattern.compile("//(.)\n(.*)").matcher(mathExression);
		System.out.println(m.matches());
		if (m.matches()) {
			System.out.println("여긴 걸리려나");
			String customDelimiter = m.group(1);
			String[] tokens = m.group(2).split(customDelimiter);
			// 덧셈 구현
		}
		//2. 커스텀 구분자 아닌 경우, 콤마 아니면 : 콜론으로 구분되는데
		// 구분자자체가 정규식을 받으므로, ",|:" 의 작대기 1개로 줘서 split하면 된다.
		System.out.println("정규표현식에 안걸렸다.");
		return mathExression.split(",|:");

	}

	private static void checkValidation(String mathExression) {
		// checkNull(mathExression);
		//
		//
		// 예외발생은 음수시 -> RUntime만..
		// checkNegativeNumber(mathExression)
	}

	private static void checkNull(String mathExression) {
		if (isNull(mathExression)) {
			//TODO: result에 0을 반환
			throw new IllegalArgumentException("수식을 입력하세요");
		}
	}

	private static boolean isNull(String mathExression) {
		return mathExression == null || mathExression.trim().isEmpty();
	}

	public List<Integer> getNumbers() {
		return numbers;
	}
}
