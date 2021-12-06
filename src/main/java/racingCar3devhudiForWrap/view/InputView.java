package racingCar3devhudiForWrap.view;

import java.util.Arrays;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import racingCar3devhudiForWrap.utils.Util;

public class InputView {

	public static String getInput() {
		return Console.readLine();
	}

	public static List<String> getNames() {
		try {
			String inputValue = InputView.getInput();
			Util.checkNamesValidation(inputValue);
			return Arrays.asList(inputValue.split(Util.DELIMETER));
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			return InputView.getNames();
		}
	}

	public static int getCount() {
		try {
			String inputValue = InputView.getInput();
			Util.checkCountValidation(inputValue);
			return Integer.parseInt(inputValue);
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			return getCount();
		}
	}

	public static List<String> getNumbers() {
		try {
			String inputValue = InputView.getInput();
			String delimeter = ","; // 1. 상수로 빼기 (default "" -> 스페이스 주고 입력하면 " " )
			Util.checkNumbersValidation(inputValue, delimeter); // split은 체크할때마다 매번 한다.
			return Arrays.asList(inputValue.split(delimeter));
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			return InputView.getNumbers();
		}
	}

	public static String getName() {
		try {
			String inputValue = InputView.getInput();
			// 택1 ( split전에 공백제거 할지말지) -> 안하면 함수도 삭제
			// -> 공백이 delimeter면 ㄴㄴㄴ
			// String inputValue = removeSpace(InputView.getInput());

			// 검증정리
			// 문자열 숫자 여러개(검증전 공백제거 ) -> null or empty -> 딜리미터가 첫값인지 검사 ->  딜리미터 기준 덩어리 (최소)갯수 검사(2인이상)
			//   -> 딜리미터기준 덩어리 중복검사 -> 딜리미터 기준 덩어리 (정확히n개)갯수 검사 -> 개별 포맷검사 -> 개별 범위 검사
			// 문자열     여러개(검증전 공백제거 ) ->  null or empty -> 딜리미터가 첫값인지 검사 -> 딜리미터 기준 갯수검사 -> 딜리미터 기준 중복검사
			// 문자열 숫자  1개(검증전 공백제거 ) -> null or empty -> (길이:입력갯수) -> format -> 범위 (-> 여러개만 중복검사)
			// 문자열      1개 (검증전 공백제거 ) ->  null or empty -> 길이  ==  입력갯수검사  ->  공백포함 검사  (-> 필요시 1단어이지만, 알파벳 중복검사)
			checkNameValidation(inputValue);

			return inputValue.trim();
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			return getName();
		}
	}

	private static void checkNameValidation(String inputValue) {
		// 문자열      1개 (검증전 공백제거 ) ->  null or empty -> 길이=입력갯수검사  ->  공백포함 검사  (-> 필요시 1단어인데 알파벳 중복검사)
		//TODO: 검증메서드 추가
		// ============= n자리 문자열 1개 (2자리이상 가능)개 입력 받을 때 ========================
		//1. null검사
		checkNullOrEmpty(inputValue);
		//2. 길이=입력(알파벳)갯수 제한.
		// -> 최소 <= ~ <= 최대 갯수가 default. ->  필요시 특정갯수(==) or 최소 몇글자 이상( 2 < ) 등으로 수정해야함.
		checkValidLengthOfName(inputValue);
		//3. 여기만 있는, trim후 내부 공백포함 검사
		checkIncludeSpace(inputValue);
		// 3.5+ . 포맷검사 -> 숫자 금지시킬때??
		checkValidFormatOfName(inputValue);

		//4. 1 word지만, 알파벳 중복을 검사한다면
		checkDuplicatesOfName(inputValue);

	}

	private static void checkNullOrEmpty(String inputValue) {
		if (inputValue == null || inputValue.trim().isEmpty()) {
			throw new IllegalArgumentException("빈칸 입력은 허용하지 않는다.");
		}
	}

	private static void checkValidLengthOfName(String inputValue) {
		int inputValueLength = inputValue.length();
		if (!(2 <= inputValueLength && inputValueLength <= 5)) {
			throw new IllegalArgumentException(2 + "~" + 5 + " 글자 범위 내에서 입력하세요.");
		}
		;
	}

	private static void checkIncludeSpace(String inputValue) {
		if (inputValue.trim().contains(" ")) {
			throw new IllegalArgumentException("이름에 공백이 포함될 수 없습니다.");
		}
	}

	private static void checkValidFormatOfName(String inputValue) {
		if (inputValue.chars().anyMatch(Character::isDigit)) {
			throw new IllegalArgumentException("숫자는 허용하지 않습니다.");
		}
	}

	private static void checkDuplicatesOfName(String inputValue) {
		if (Arrays.stream(inputValue.trim().split("")).distinct().count() != inputValue.trim().length()) {
			throw new IllegalArgumentException("중복값을 입력할 수 없습니다.");
		}
	}

}
