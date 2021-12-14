package vendingMachineByMe.utils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Randoms;
import vendingMachineByMe.domain.VendingMachine;

public class Util {
	public static int getPickRandomNumberInList(List<Integer> numbers) {
		return Randoms.pickNumberInList(numbers);
	}

	public static void checkAmountValidation(String inputValue) {
		checkNullOrEmpty(inputValue);
		checkValidFormatOfAmount(inputValue);
		checkValidRangeAmount(inputValue);
		checkValidMultiply(inputValue);

	}

	private static void checkValidFormatOfAmount(String inputValue) {
		if (!(inputValue.chars().allMatch(Character::isDigit))) {
			throw new IllegalArgumentException("시도 횟수는 숫자여야 한다.");
		}
	}

	private static void checkValidRangeAmount(String inputValue) {
		int number = Integer.parseInt(inputValue);
		if (!(10 <= number)) {
			throw new IllegalArgumentException("정상 범위(" + 10 + "원이상)가 아닙니다");
		}
	}

	private static void checkValidMultiply(String inputValue) {
		int number = Integer.parseInt(inputValue);
		if (!(number % 10 == 0)) {
			throw new IllegalArgumentException("10원단위로 입력해주세요");
		}
	}

	public static String removeSpace(String input) {
		return input.replaceAll(" ", "");
	}

	public static void checkSaleInfoValidation(String inputValue, String delimeter) {
		checkNullOrEmpty(inputValue); // 1개짜리들도 다 공통이라 no delimeter
		checkValidFirstValue(inputValue, delimeter);
		checkDuplicatesOfSaleInfo(inputValue, delimeter);

		//TODO 7: 결론- 래핑까지하고 거기서 검증하며 너무복잡 -> convert만 래핑에서하고
		//-> 검증은 쪼개서 하더라도 여기서 다하자.
		//-> 컨버트만.. String -> 등을 래핑클래스에서
		//--> 외부로 return은,  delimeter로 쪼갠 것만 넘겨준다.
		// checkPatternOfSaleInfo(inputValue, delimeter);

		// TODO 12: 문제요구사항> 상품 가격은 100원부터 시작하며, 10원으로 나누어떨어져야 한다.
		// -> 패턴검사 통과시로 옮김..
		// checkDemands(inputValue, delimeter);
		checkPatternOfSaleInfo(inputValue, delimeter);

	}

	private static void checkPatternOfSaleInfo(String inputValue, String delimeter) {
		//1. 쪼개기
		List<String> splitedInputValue = Arrays.stream(inputValue.split(delimeter))
			.map(String::trim)  // split + 개별 trim()적용 -> list로 모으기
			// .peek(k -> System.out.println("쪼갠 것 확인 : " + k))
			.collect(Collectors.toList());

		//2. 패턴확인하기
		Pattern pattern = Pattern.compile("\\[([가-힣]+),([0-9]+),([0-9]+)]"); //[가,1000,20];[나,2000,11]

		boolean isMatched = splitedInputValue.stream()
			.allMatch(stringValue -> pattern.matcher(stringValue).find());

		if (!isMatched) {
			throw new IllegalArgumentException("패턴에 맞지 않는 입력입니다.");
		}

		// System.out.println("패턴은 다 통과");

		//3. 패턴확인후 추출하기 with match변수 필수
		boolean isDemanded = splitedInputValue.stream()
			.mapToInt(stringValue -> {
				// 내용추출할려면 matcher 변수는 필수.!!
				Matcher matcher = pattern.matcher(stringValue);
				if (matcher.find()) {
					String name = matcher.group(1);
					String amount = matcher.group(2);
					String count = matcher.group(3);
					return Integer.valueOf(amount);
				}
				return 0;
			})
			// .peek(a -> System.out.println("검사에드가는 변수 " + a)) // 로그
			.allMatch(amount -> amount >= 100 && amount % 10 == 0);

		if (!isDemanded) {
			throw new IllegalArgumentException("상품 가격은 100원부터 시작하며, 10원으로 나누어떨어져야 한다.");
		}

		// System.out.println("검사까지 다 통과");
	}

	private static void checkValidFirstValue(String inputValue, String delimeter) {
		if (inputValue.charAt(0) == delimeter.charAt(0)) {
			throw new IllegalArgumentException("정상적인 이름을 입력하세요.");
		}
	}

	private static void checkDuplicatesOfSaleInfo(String inputValue, String delimeter) {
		List<String> splitedInputValue = Arrays.stream(inputValue.split(delimeter))
			.map(String::trim)  // split + 개별 trim()적용 -> list로 모으기
			.collect(Collectors.toList());
		if (splitedInputValue.stream().distinct().count() != splitedInputValue.size()) {
			throw new IllegalArgumentException("중복값을 입력할 수 없습니다.");
		}
	}

	public static void checkCountValidation(String count) {
		//TODO: 검증메서드 추가
		// ============= n자리 문자열 숫자 1개 (2자리이상 가능)개 입력 받을 때 ========================
		checkNullOrEmpty(count);
		// checkValidRangeCount(count);
		checkValidFormatOfCount(count);

	}

	private static void checkValidFormatOfCount(String count) {
		// System.out.println("검사에 걸리는 count : " + count);
		if (!(count.chars().allMatch(Character::isDigit))) {
			throw new IllegalArgumentException("시도 횟수는 숫자여야 한다.");
		}
	}

	private static void checkValidRangeCount(String count) {
		int number = Integer.parseInt(count);
		if (!(1 <= number)) {
			throw new IllegalArgumentException("정상 범위(" + 1 + "이상)가 아닙니다");
		}
	}

	public static void checkSaleNameValidation(String inputValue) {
		checkNullOrEmpty(inputValue);
		checkValidLengthOfSaleName(inputValue);
		checkIncludeSpace(inputValue);
		//TODO 11. sale품목에 있는지 검사하고 싶은데.. view에선 안될 듯..
		// new)
		checkValidExist(inputValue); // 싱글톤속 counerMap에 해당상품 존재유무
		checkValidCount(inputValue); // 싱글톤속 counterMap에 해당상품 1개이상 있는지 유무
		// new)또 추가... 존재하고 1개이상 있는데, 내돈이 그것보다 큰지
		checkValidPurchasable(inputValue);

	}

	private static void checkValidPurchasable(String inputValue) {
		VendingMachine vendingMachine = VendingMachine.getInstance();
		if (!(vendingMachine.isPurchasable(inputValue))){
			throw new IllegalArgumentException("비싸서 구매 불가능");
		};
	}

	private static void checkNullOrEmpty(String inputValue) {
		if (inputValue == null || inputValue.trim().isEmpty()) {
			throw new IllegalArgumentException("빈칸 입력은 허용하지 않는다.");
		}
	}

	private static void checkValidLengthOfSaleName(String inputValue) {
		int inputValueLength = inputValue.length();
		if (!(1 <= inputValueLength)) {
			throw new IllegalArgumentException(1 + " 글자 이상 입력하세요.");
		}
		;
	}

	private static void checkIncludeSpace(String inputValue) {
		if (inputValue.trim().contains(" ")) {
			throw new IllegalArgumentException("공백이 포함될 수 없습니다.");
		}
	}

	private static void checkValidExist(String inputValue) {
		VendingMachine vendingMachine = VendingMachine.getInstance();
		if (!(vendingMachine.isExist(inputValue))) {
			throw new IllegalArgumentException(inputValue + "은(는) 존재하지 않습니다. 다시 입력해주세요.");
		}
	}
	// 싱글톤 내부 isExist ( )  ->  this.XXXmap.isExist ( )로 가도록 쓴다. (미리 정의)

	private static void checkValidCount(String inputValue) {
		VendingMachine vendingMachine = VendingMachine.getInstance();
		if (!(vendingMachine.isAvailable(inputValue))) {
			throw new IllegalArgumentException(inputValue + "은(는) 현재 0 입니다. 다시 입력해주세요.");
		}
	}
	// 싱글톤 내부 isAvailable ->  this.XXXmap.isAvailable( ) 로 가도록 쓴다. (미리 정의)

}
