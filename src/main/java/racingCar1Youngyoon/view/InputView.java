package racingCar1Youngyoon.view;

import java.util.Arrays;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
	public static String getInput() {
		return Console.readLine();
	}

	public static List<String> getNames() {
		//3. 이게 에러나면 입력받기 무한반복해야함. -> 여기서 조건(결과값)변수 + while
		// -> checkXXXX 메서드가 결과반환해줘서 조건변수 업데이트시켜야함
		boolean isPassValidation = false;
		String input = null;
		while (!isPassValidation) {
			input = Console.readLine();
			input = removeSpace(input);
			//4. 벨리데이션 다 통과시에만 isPassValid에 true주면 됨
			// -> default로  통과못할시  print후 false유지되서 계속 돌기
			isPassValidation = checkValidation(input);
		}

		return Arrays.asList(input.split(","));

	}

	private static String removeSpace(String input) {
		return input.replaceAll(" ", "");
	}

	private static boolean checkValidation(String input) {
		if (checkNull(input) && checkLength(input)) {
			return true;
		}
		return false;
	}

	private static boolean checkLength(String input) {
		if (Arrays.stream(input.split(","))
			.anyMatch(name -> name.length() > 5)) {
			OutputView.printOverLength();
			return false;
		}
		return true;
	}

	private static boolean checkNull(String input) {
		if (input == null || input.isEmpty()) {
			// throw new IllegalArgumentException("[ERROR] ");
			OutputView.printNullError();
			return false;
		}
		// 5. TODO: 각각의 이름도 빈칸인 이름도 체크해야한다.
		if (Arrays.stream(input.split(","))
			.mapToInt(name -> name.length())
			.anyMatch(length -> length < 1)) {
			OutputView.printNameNullError();
			return false;
		}
		return true;
	}
}
