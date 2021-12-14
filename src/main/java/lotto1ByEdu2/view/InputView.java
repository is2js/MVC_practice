package lotto1ByEdu2.view;

import java.util.Arrays;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import lotto1ByEdu2.utils.Util;

public class InputView {

	// 1. 변수까지 합해서 ctrl+alt+c로 상수로 빼기 (default "" -> 스페이스 주고 입력하면 " " )
	public static final String DELIMETER = ",";

	public static String getInput() {
		return Console.readLine();
	}

	public static List<Integer> getWinnerNumbers() {
		try {
			String inputValue = Util.removeSpace(InputView.getInput());
			checkWinnerNumbersValidation(inputValue, DELIMETER);
			return Util.convertStringToIntList(Arrays.asList(inputValue.split(DELIMETER)));
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			return InputView.getWinnerNumbers();
		}
	}

	private static void checkWinnerNumbersValidation(String inputValue, String delimeter) {
		Util.checkNullOrEmpty(inputValue); // 1개짜리들도 다 공통이라 no delimeter
		Util.checkValidFirstValue(inputValue, delimeter);
		Util.checkValidCountOfWinnerNumbers(inputValue, delimeter);
		Util.checkValidLengthOfWinnerNumbers(inputValue, delimeter);
		Util.checkValidFormatOfWinnerNumbers(inputValue, delimeter);
		Util.checkValidRangeOfWinnerNumbers(inputValue, delimeter);

	}

	public static int getManualCount() {
		String inputValue = InputView.getInput();
		checkManualCountValidation(inputValue);
		return Integer.parseInt(inputValue);
	}

	private static void checkManualCountValidation(String inputValue) {
		Util.checkNullOrEmpty(inputValue);
		Util.checkValidFormatOfManualCount(inputValue);
		Util.checkValidRangeManualCount(inputValue);
		//TODO: 래핑클래스에서 범위를..  구매금액 -> 최대로또갯수보다는 작게.. 추가체크해줘야함..
	}

	public static int getBonus() {
		try {
			String inputValue = InputView.getInput();
			// 택1 ( split전에 공백제거 할지말지) -> 안하면 함수도 삭제
			// -> 공백이 delimeter면 ㄴㄴㄴ
			// String inputValue = removeSpace(InputView.getInput());

			// (검증전 공백제거 ) -> null or empty -> 길이 -> format -> 범위 -> ( 여러개만 중복검사)
			checkBonusValidation(inputValue);

			return Integer.parseInt(inputValue);
			// return input;
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			return getBonus();
		}
	}

	private static void checkBonusValidation(String inputValue) {
		checkNullOrEmpty(inputValue);
		checkValidFormatOfBonus(inputValue);
		checkValidRangeBonus(inputValue);

	}

	private static void checkNullOrEmpty(String inputValue) {
		if (inputValue == null || inputValue.trim().isEmpty()) {
			throw new IllegalArgumentException("빈칸 입력은 허용하지 않는다.");
		}
	}

	// 혹시나 n자리 1개의 문자열 숫자받는데, [입력갯수 제한]이 있을 때만..
	// private static void checkValidLengthOfBonus(String inputValue) {
	// 	int inputValueLength = inputValue.length();
	// 	if ( 1 <=inputValueLength && inputValueLength <= 100);
	// }

	private static void checkValidFormatOfBonus(String inputValue) {
		if (!(inputValue.chars().allMatch(Character::isDigit))) {
			throw new IllegalArgumentException("시도 횟수는 숫자여야 한다.");
		}
	}

	private static void checkValidRangeBonus(String inputValue) {
		int number = Integer.parseInt(inputValue);
		if (!(1 <= number && number <= 45)) {
			throw new IllegalArgumentException("정상 범위(" + 1 + "~" + 45 + ")가 아닙니다");
		}
	}

}
