package lotto2ByMe.view;

import java.util.Arrays;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import lotto2ByMe.utils.Util;

public class InputView {

	// 1. 변수까지 합해서 ctrl+alt+c로 상수로 빼기 (default "" -> 스페이스 주고 입력하면 " " )
	public static final String DELIMETER = ",";

	public static String getInput() {
		return Console.readLine();
	}

	public static int getMoney() {
		try {
			String inputValue = Util.removeSpace(InputView.getInput());
			Util.checkMoneyValidation(inputValue);
			return Integer.parseInt(inputValue);
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			return getMoney();
		}
	}

	// 혹시나 n자리 1개의 문자열 숫자받는데, [입력갯수 제한]이 있을 때만..
	// private static void checkValidLengthOfMoney(String inputValue) {
	// 	int inputValueLength = inputValue.length();
	// 	if (  <=inputValueLength && inputValueLength <= );
	// }

	public static List<String> getWinNumbers() {
		try {
			String inputValue = Util.removeSpace(InputView.getInput());
			Util.checkWinNumbersValidation(inputValue, DELIMETER); // split은 체크할때마다 매번 한다.

			// 결국엔 String으로 반환. 필요시 일급컬렉션으로 변환
			return Arrays.asList(inputValue.split(DELIMETER));
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			return InputView.getWinNumbers();
		}
	}

	public static int getManualCount(int countTotal) {
		try {
			String inputValue = InputView.getInput();
			Util.checkManualCountValidation(inputValue, countTotal);
			return Integer.parseInt(inputValue);
			// return input;
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			return getManualCount(countTotal);
		}
	}

	// 혹시나 n자리 1개의 문자열 숫자받는데, [입력갯수 제한]이 있을 때만..
	// private static void checkValidLengthOfManualCount(String inputValue) {
	// 	int inputValueLength = inputValue.length();
	// 	if ( 1 <=inputValueLength && inputValueLength <= 1000);
	// }

	public static int getNumber() {
		try {
			String inputValue = InputView.getInput();
			Util.checkNumberValidation(inputValue);
			return Integer.parseInt(inputValue);
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			return getNumber();
		}
	}

}
