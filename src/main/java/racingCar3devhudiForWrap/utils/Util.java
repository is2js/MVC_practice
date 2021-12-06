package racingCar3devhudiForWrap.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Randoms;

public class Util {
	public static final String DELIMETER = ",";

	public static void checkNamesValidation(String inputValue) {
		checkNullOrEmpty(inputValue); // 1개짜리들도 다 공통이라 no delimeter
		checkValidFirstValue(inputValue, DELIMETER);
		checkValidCount(inputValue, DELIMETER);
		checkDuplicates(inputValue, DELIMETER);
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

	private static void checkValidCount(String inputValue, String delimeter) {
		if (inputValue.split(delimeter).length < 2) {
			throw new IllegalArgumentException("최소 2개 이상 입력하세요.");
		}
	}

	private static void checkDuplicates(String inputValue, String delimeter) {
		List<String> splitedInputValue = Arrays.stream(inputValue.split(delimeter))
			.map(String::trim)  // split + 개별 trim()적용 -> list로 모으기
			.collect(Collectors.toList());
		if (splitedInputValue.stream().distinct().count() != splitedInputValue.size()) {
			throw new IllegalArgumentException("중복값을 입력할 수 없습니다.");
		}
	}

	public static void checkCountValidation(String inputValue) {
		checkNullOrEmpty(inputValue);
		checkValidFormatOfCount(inputValue);
		checkValidRangeCount(inputValue);

	}

	private static void checkValidFormatOfCount(String inputValue) {
		if (!(inputValue.chars().allMatch(Character::isDigit))) {
			throw new IllegalArgumentException("시도 횟수는 숫자여야 한다.");
		}
	}

	private static void checkValidRangeCount(String inputValue) {
		int number = Integer.parseInt(inputValue);
		if (!(1 <= number && number <= 9)) {
			throw new IllegalArgumentException("정상 범위(" + 1 + "~" + 9 + ")가 아닙니다");
		}
	}

	//참고1) 문숫: 검증전공백제거 -> null -> 길이 -> (문숫)format -> 범위 -> (중복여부 등 기타)
	//참고2) 문여: 검증전공백제거 -> null -> 첫번째값 정상? -> 2개이상? -> 2개이상인데 중복여부? -> 기타.
	public static void checkNumbersValidation(String inputValue, String delimeter) {
		//TODO: getMany(딜리미터) 용 검증메서드 추가
		//1. 공통 null체크
		checkNullOrEmpty(inputValue); // 1개짜리들도 다 공통이라 no delimeter
		//여러개입력시 공통사항.1.5
		checkValidFirstValue(inputValue, delimeter);

		// =======2. length(입력갯수, 길이)============
		//-> 최소 2개 입력해야한다면
		checkValidCountOfNumbers(inputValue, delimeter);
		//-> 2개 이상인데, 중복은 아닌지 체크해야한다면
		checkDuplicatesOfNumbers(inputValue, delimeter);
		//-> fix(입력갯수)  n개 제한의  문자열숫자라면
		checkValidLengthOfNumbers(inputValue, delimeter);
		//===================================

		//3. format ( split 한 string -> string.chars -> 확인 )
		checkValidFormatOfNumbers(inputValue, delimeter);

		//4. range ( split string -> [if string] AllMatch -> [if2 int] isXXX
		checkValidRangeOfNumbers(inputValue, delimeter);

	}

	private static void checkValidCountOfNumbers(String inputValue, String delimeter) {
		int inputLength = inputValue.split(delimeter).length;
		if (inputLength < 2) {
			throw new IllegalArgumentException("최소 " + 2 + "개 이상 입력하세요.");
		}
	}

	private static void checkDuplicatesOfNumbers(String inputValue, String delimeter) {
		// split + 개별 trim()적용 -> list로 모으기
		// list.size() 와 list.stream.distince.count와의 비교를 위해 list 하나 생성
		List<String> splitedTrimedInputValue = new ArrayList<>();
		for (String s : inputValue.split(delimeter)) {
			String trim = s.trim();
			splitedTrimedInputValue.add(trim);
		}
		if (splitedTrimedInputValue.stream().distinct().count() != splitedTrimedInputValue.size()) {
			throw new IllegalArgumentException("중복값을 입력할 수 없습니다.");
		}
	}

	private static void checkValidLengthOfNumbers(String inputValue, String delimeter) {
		int inputLength = inputValue.split(delimeter).length;
		if (!(inputLength == 4)) {
			throw new IllegalArgumentException(4 + "개의 숫자를 입력하세요");
		}
	}

	private static void checkValidFormatOfNumbers(String inputValue, String delimeter) {

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

	private static void checkValidRangeOfNumbers(String inputValue, String delimeter) {
		boolean isAllValidRange = Arrays.stream(inputValue.split(delimeter))
			.map(String::trim)
			.allMatch(numberString -> isValidRange(numberString));

		if (!(isAllValidRange)) {
			throw new IllegalArgumentException(1 + "~" + 9 + " 범위내에서 입력하세요.");
		}

	}

	private static boolean isValidRange(String numberString) {
		int number = Integer.parseInt(numberString);
		return 1 <= number && number <= 9;
	}

	public static int getRandomNumber() {
		return Randoms.pickNumberInRange(0, 9);
	}
}
