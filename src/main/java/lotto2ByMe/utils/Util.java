package lotto2ByMe.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Randoms;

public class Util {
	public static int generateLottoNumber() {
		return Randoms.pickNumberInRange(1, 45);
	}

	public static String removeSpace(String input) {
		return input.replaceAll(" ", "");
	}

	public static void checkMoneyValidation(String inputValue) {
		checkNullOrEmpty(inputValue);
		checkValidFormatOfMoney(inputValue);
		//TODO: 1000원단위로 입력
		checkValidUnit(inputValue);

	}

	private static void checkValidUnit(String inputValue) {
		if (Integer.parseInt(inputValue) % 1000 != 0) {
			throw new IllegalArgumentException("천원단위로 입력하세요");
		}
	}

	private static void checkNullOrEmpty(String inputValue) {
		if (inputValue == null || inputValue.trim().isEmpty()) {
			throw new IllegalArgumentException("빈칸 입력은 허용하지 않는다.");
		}
	}

	private static void checkValidFormatOfMoney(String inputValue) {
		if (!(inputValue.chars().allMatch(Character::isDigit))) {
			throw new IllegalArgumentException("222시도 횟수는 숫자여야 한다.");
		}
	}

	public static void checkWinNumbersValidation(String inputValue, String delimeter) {
		checkNullOrEmpty(inputValue); // 1개짜리들도 다 공통이라 no delimeter
		checkValidFirstValue(inputValue, delimeter);
		checkValidLengthOfWinNumbers(inputValue, delimeter);
		checkValidFormatOfWinNumbers(inputValue, delimeter);
		checkValidRangeOfWinNumbers(inputValue, delimeter);

	}

	private static void checkValidFirstValue(String inputValue, String delimeter) {
		if (inputValue.charAt(0) == delimeter.charAt(0)) {
			throw new IllegalArgumentException("정상적인 이름을 입력하세요.");
		}
	}

	private static void checkDuplicatesOfWinNumbers(String inputValue, String delimeter) {
		List<String> splitedTrimedInputValue = Arrays.stream(inputValue.split(delimeter))
			.map(String::trim)
			.collect(Collectors.toList());
		if (splitedTrimedInputValue.stream().distinct().count() != splitedTrimedInputValue.size()) {
			throw new IllegalArgumentException("중복값을 입력할 수 없습니다.");
		}
	}

	private static void checkValidLengthOfWinNumbers(String inputValue, String delimeter) {
		int inputLength = inputValue.split(delimeter).length;
		if (!(inputLength == 6)) { //TODO: 위와 배반관계인, 정확히 N개로 입력제한
			throw new IllegalArgumentException(6 + "개의 숫자를 입력하세요");
		}
	}

	private static void checkValidFormatOfWinNumbers(String inputValue, String delimeter) {

		boolean isNumber = Arrays.stream(inputValue.split(delimeter))
			.map(String::trim)
			.allMatch(numberString -> isNumber(numberString));
		if (!(isNumber)) {
			throw new IllegalArgumentException("숫자를 입력하세요.");
		}
	}

	private static boolean isNumber(String numberString) {
		return numberString.chars().allMatch(Character::isDigit);
	}

	private static void checkValidRangeOfWinNumbers(String inputValue, String delimeter) {
		boolean isAllValidRange = Arrays.stream(inputValue.split(delimeter))
			.map(String::trim)
			.allMatch(numberString -> isValidRange(numberString));

		if (!(isAllValidRange)) { //TODO: 최소 / 최대 범위 -> 숫자 1개 (2자리일수도있음 -> 10이상가능)
			throw new IllegalArgumentException(1 + "~" + 46 + " 범위내에서 입력하세요.");
		}

	}

	private static boolean isValidRange(String numberString) {
		int number = Integer.parseInt(numberString);
		return 1 <= number && number <= 46;
	}

	public static void checkManualCountValidation(String inputValue, int countTotal) {
		checkNullOrEmpty(inputValue);
		checkValidFormatOfManualCount(inputValue);
		checkValidRangeManualCount(inputValue, countTotal);

	}

	private static void checkValidFormatOfManualCount(String inputValue) {
		if (!(inputValue.chars().allMatch(Character::isDigit))) {
			throw new IllegalArgumentException("111시도 횟수는 숫자여야 한다.");
		}
	}

	private static void checkValidRangeManualCount(String inputValue, int countTotal) {
		int number = Integer.parseInt(inputValue);
		if (!(0 <= number && number <= countTotal)) { //TODO: moneyTOLottocount 필요함.
			throw new IllegalArgumentException("정상 범위(" + 0 + "~" + countTotal + ")가 아닙니다");
		}
	}

	public static void checkNumberValidation(String inputValue) {
		checkNullOrEmpty(inputValue);
		checkValidLengthOfNumber(inputValue);
		checkValidFormatOfNumber(inputValue);
		checkValidRangeNumber(inputValue);

	}

	private static void checkValidLengthOfNumber(String inputValue) {
		int inputValueLength = inputValue.length();
		if (!(1 <= inputValueLength && inputValueLength <= 2)) {
			throw new IllegalArgumentException("1~2자리 숫자만 입력가능합니다.");
		}
		;
	}

	private static void checkValidFormatOfNumber(String inputValue) {
		if (!(inputValue.chars().allMatch(Character::isDigit))) {
			throw new IllegalArgumentException("시도 횟수는 숫자여야 한다.");
		}
	}

	private static void checkValidRangeNumber(String inputValue) {
		int number = Integer.parseInt(inputValue);
		if (!(1 <= number && number <= 45)) {
			throw new IllegalArgumentException("정상 범위(" + 1 + "~" + 45 + ")가 아닙니다");
		}
	}
}
