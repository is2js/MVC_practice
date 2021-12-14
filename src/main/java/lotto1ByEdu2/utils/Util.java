package lotto1ByEdu2.utils;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Randoms;
import lotto1ByEdu2.domain.LottoNumbers;

public class Util {
	public static LottoNumbers generateLottoNumbers() {

		LinkedHashSet<Integer> hashSet = new LinkedHashSet<>();
		while (hashSet.size() < 6) {
			int randomNumber = Randoms.pickNumberInRange(1, 14);
			hashSet.add(randomNumber);
		}
		return LottoNumbers.from(hashSet.stream().collect(Collectors.toList()));

	}

	public static List<Integer> convertStringToIntList(List<String> list) {
		return list.stream()
			.mapToInt(Integer::parseInt)
			.boxed()
			.collect(Collectors.toList());
	}

	public static String removeSpace(String input) {
		return input.replaceAll(" ", "");
	}

	public static void checkNullOrEmpty(String inputValue) {
		if (inputValue == null || inputValue.trim().isEmpty()) {
			throw new IllegalArgumentException("빈칸 입력은 허용하지 않는다.");
		}
	}

	public static void checkValidFirstValue(String inputValue, String delimeter) {
		if (inputValue.charAt(0) == delimeter.charAt(0)) {
			throw new IllegalArgumentException("정상적인 이름을 입력하세요.");
		}
	}

	public static void checkValidCountOfWinnerNumbers(String inputValue, String delimeter) {
		int inputLength = inputValue.split(delimeter).length;
		if (inputLength < 2) { //TODO: 2 <- 입력, 아래와 배반관계, 최소 N개이상할 때의 N
			throw new IllegalArgumentException("최소 " + 2 + "개 이상 입력하세요.");
		}
	}

	public static void checkValidLengthOfWinnerNumbers(String inputValue, String delimeter) {
		int inputLength = inputValue.split(delimeter).length;
		if (!(inputLength == 6)) { //TODO: 위와 배반관계인, 정확히 N개로 입력제한
			throw new IllegalArgumentException(6 + "개의 숫자를 입력하세요");
		}
	}

	public static void checkValidFormatOfWinnerNumbers(String inputValue, String delimeter) {

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

	public static void checkValidRangeOfWinnerNumbers(String inputValue, String delimeter) {
		boolean isAllValidRange = Arrays.stream(inputValue.split(delimeter))
			.map(String::trim)
			.allMatch(numberString -> isValidRange(numberString));

		if (!(isAllValidRange)) { //TODO: 최소 / 최대 범위 -> 숫자 1개 (2자리일수도있음 -> 10이상가능)
			throw new IllegalArgumentException(1 + "~" + 45 + " 범위내에서 입력하세요.");
		}

	}

	private static boolean isValidRange(String numberString) {
		int number = Integer.parseInt(numberString);
		return 1 <= number && number <= 45;
	}

	public static void checkValidFormatOfManualCount(String inputValue) {
		if (!(inputValue.chars().allMatch(Character::isDigit))) {
			throw new IllegalArgumentException("시도 횟수는 숫자여야 한다.");
		}
	}

	public static void checkValidRangeManualCount(String inputValue) {
		int number = Integer.parseInt(inputValue);
		if (!(0 <= number)) {
			throw new IllegalArgumentException("정상 범위(" + 0 + "~)가 아닙니다");
		}
	}
}
