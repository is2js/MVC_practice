package racingCar2YoungyoonForStream.view;

import java.util.Arrays;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
	public static String getInput() {
		return Console.readLine();
	}

	//참고) 검증전공백제거 -> null -> 길이 -> format -> 범위 -> 중복여부 등 기타
	public static List<String> getNames() {
		try {
			String inputValue = InputView.getInput();
			// String inputValue = removeSpace(InputView.getInput());
			checkValidation(inputValue);
			return Arrays.asList(inputValue.split(","));
		} catch (IllegalArgumentException e) {
			OutputView.printError(e);
			return InputView.getNames();
		}
	}

	// private static String removeSpace(String input) {
	// 	return input.replaceAll(" ", "");
	// }

	private static void checkValidation(String input) {
		checkNull(input);
		//TODO: 검증메서드 추가
		// checkLength(input);
	}

	private static void checkNull(String input) {
		if (input == null || input.isEmpty()) {
			throw new IllegalArgumentException("빈칸 입력은 허용하지 않는다.");
		}
	}

	//참고) 검증전공백제거 -> null -> 길이 -> format -> 범위 -> 중복여부 등 기타
	public static int getCount() {
		try {
			String input = InputView.getInput();
			//String input = removeSpace(InputView.getInput());
			checkCountValidation(input);
			return Integer.parseInt(input);
			// return input;
		} catch (IllegalArgumentException e) {
			OutputView.printError(e);
			return getCount();
		}
	}

	private static void checkCountValidation(String input) {
		checkNull(input);
		//TODO: 검증메서드 추가
		// checkLength(input);
	}

}
