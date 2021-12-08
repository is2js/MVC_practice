package baseball3streamAndEnum.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

	public static final String DELIMETER = "";

	public static String getInput() {
		return Console.readLine();
	}

	public static List<String> getBalls() {
		try {
			String inputValue = InputView.getInput();
			checkBallsValidation(inputValue, DELIMETER);
			return Arrays.asList(inputValue.split(DELIMETER));
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			return InputView.getBalls();
		}
	}

	private static void checkBallsValidation(String inputValue, String delimeter) {
		checkNullOrEmpty(inputValue); // 1개짜리들도 다 공통이라 no delimeter
		checkValidCountOfBalls(inputValue, delimeter);
		checkDuplicatesOfBalls(inputValue, delimeter);
		checkValidLengthOfBalls(inputValue, delimeter);
		checkValidFormatOfBalls(inputValue, delimeter);
		checkValidRangeOfBalls(inputValue, delimeter);

	}

	private static void checkNullOrEmpty(String inputValue) {
		if (inputValue == null || inputValue.trim().isEmpty()) {
			throw new IllegalArgumentException("빈칸 입력은 허용하지 않는다.");
		}
	}

	private static void checkValidFirstValue(String inputValue, String delimeter) {
		if (inputValue.charAt(0) == delimeter.charAt(0)) {
			throw new IllegalArgumentException("정상적인 이름을 입력하세요.");
		}
	}

	private static void checkValidCountOfBalls(String inputValue, String delimeter) {
		int inputLength = inputValue.split(delimeter).length;
		if (inputLength < 2) { //TODO: 2 <- 입력, 아래와 배반관계, 최소 N개이상할 때의 N
			throw new IllegalArgumentException("최소 " + 3 + "개 이상 입력하세요.");
		}
	}

	private static void checkDuplicatesOfBalls(String inputValue, String delimeter) {
		List<String> splitedTrimedInputValue = Arrays.stream(inputValue.split(delimeter))
			.map(String::trim)
			.collect(Collectors.toList());
		if (splitedTrimedInputValue.stream().distinct().count() != splitedTrimedInputValue.size()) {
			throw new IllegalArgumentException("중복값을 입력할 수 없습니다.");
		}
	}

	private static void checkValidLengthOfBalls(String inputValue, String delimeter) {
		int inputLength = inputValue.split(delimeter).length;
		if (!(inputLength == 3)) { //TODO: 위와 배반관계인, 정확히 N개로 입력제한
			throw new IllegalArgumentException(3 + "개의 숫자를 입력하세요");
		}
	}

	private static void checkValidFormatOfBalls(String inputValue, String delimeter) {

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

	private static void checkValidRangeOfBalls(String inputValue, String delimeter) {
		boolean isAllValidRange = Arrays.stream(inputValue.split(delimeter))
			.map(String::trim)
			.allMatch( numberString -> isValidRange(numberString));

		if (!(isAllValidRange)) { //TODO: 최소 / 최대 범위 -> 숫자 1개 (2자리일수도있음 -> 10이상가능)
			throw new IllegalArgumentException( 1 +"~" +9 + " 범위내에서 입력하세요." );
		}

	}

	private static boolean isValidRange(String numberString) {
		int number = Integer.parseInt(numberString);
		return  1 <= number && number <= 9;
	}

	public static boolean getAnswer() {
		try {
			String inputValue = InputView.getInput();
			checkAnswerValidation(inputValue);
			int answer = Integer.parseInt(inputValue);
			if (answer == 1) {
				return true;
			}
			return false;
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			return getAnswer();
		}
	}

	private static void checkAnswerValidation(String inputValue) {
		//1. null검사
		checkNullOrEmpty(inputValue);
		checkValidFormatOfAnswer(inputValue);
		checkValidRangeAnswer(inputValue);

	}

	private static void checkValidFormatOfAnswer(String inputValue) {
		if (!(inputValue.chars().allMatch(Character::isDigit))) {
			throw new IllegalArgumentException("시도 횟수는 숫자여야 한다.");
		}
	}

	private static void checkValidRangeAnswer(String inputValue) {
		int number = Integer.parseInt(inputValue);
		if (!( 1<= number && number <= 2)) {
			throw new IllegalArgumentException("정상 범위(" + 1 + "~" + 2 + ")가 아닙니다");
		}
	}
}
