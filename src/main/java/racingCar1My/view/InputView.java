package racingCar1My.view;

import java.util.Arrays;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
	public static String getInput() {
		return Console.readLine();
	}

	public static List<String> getNames() {
		try {
			// String inputValue = InputView.getInput();
			String inputValue = removeSpace(InputView.getInput());
			checkValidation(inputValue);
			return Arrays.asList(inputValue.split(","));
		} catch (IllegalArgumentException e) {
			OutputView.printError(e);
			return InputView.getNames();
		}
	}

	private static String removeSpace(String input) {
		return input.replaceAll(" ", "");
	}

	private static void checkValidation(String input) {
		checkNull(input);
		//TODO: 검증메서드 추가
		// checkLength(input);
	}



	private static void checkNull(String input) {
		if (input == null || input.isEmpty()) {
			throw new IllegalArgumentException("빈칸 입력은 허용하지 않는다.");
		}

		// TODO: 요건 나중에 개별 객체에서 확인하면 될듯
		if (Arrays.stream(input.split(","))
			.anyMatch(String::isEmpty)) {
			throw new IllegalArgumentException("빈칸 이름은 허용하지 않는다.");
		}

	}

	private static boolean checkLength(String input) {
		if (Arrays.stream(input.split(","))
			.anyMatch(name -> name.length() > 5)) {
			throw new IllegalArgumentException("이름은 5자 이하여야 한다.");
		}
		return true;
	}

	public static Integer getCount() {
		try {
			String input = Console.readLine();
			input = removeSpace(input);
			checkCountValidation(input);
			return Integer.parseInt(input);
		} catch (IllegalArgumentException e) {
			OutputView.printError(e);
			return getCount();
		}
	}


	private static void checkCountValidation(String input) {
		checkNull(input);
		checkFormat(input);
	}

	private static void checkFormat(String input) {
		// 6. 대박 string에 chars를 붙이면, IntStraem이다!!
		// -> char매직으로서, allMatCh(Character::isDigit)를 쓸 수 있따.
		//16. 대박2
		if (!(input.chars()
			.allMatch(Character::isDigit))) {
			throw new IllegalArgumentException("시도 횟수는 숫자여야 한다.");
		}
	}
}
