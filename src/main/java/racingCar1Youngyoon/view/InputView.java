package racingCar1Youngyoon.view;

import java.util.Arrays;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
	public static String getInput() {
		return Console.readLine();
	}

	public static List<String> getNames() {
		//11. Try - catch까지 다 넣는다. -> catch에서 메소드 전체를 다시 호출하는 재귀식으로 한다.
		try {
			String input = Console.readLine();
			input = removeSpace(input);
			checkValidation(input);
			return Arrays.asList(input.split(","));
		} catch (IllegalArgumentException e) {
			OutputView.printError(e);
			// 12. 어렵지만,return이 List로되는 메소드 자체를 한번더 재귀로 호출한다.
			// 스태틱이라 딱히 객체없이, 부담없이 바로 재귀 호출
			// -> thr나서 catch에 걸리면 또 반복 vs  try가 재귀함수의 호출안되는 탈출구, base base
			// -> 재귀호출시.. return [재귀호출()]이어야한다.
			return InputView.getNames();
		}

		//3. 이게 에러나면 입력받기 무한반복해야함. -> 여기서 조건(결과값)변수 + while
		// -> checkXXXX 메서드가 결과반환해줘서 조건변수 업데이트시켜야함
		// boolean isPassValidation = false;
		// String input = null;
		// while (!isPassValidation) {
		// 	input = Console.readLine();
		// 	input = removeSpace(input);
		// 	//4. 벨리데이션 다 통과시에만 isPassValid에 true주면 됨
		// 	// -> default로  통과못할시  print후 false유지되서 계속 돌기
		// 	isPassValidation = checkValidation(input);
		// }
		//8. 그냥 thr하는 로직으로 간다.
		// String input = Console.readLine();
		// input = removeSpace(input);
		// checkValidation(input);
		// return Arrays.asList(input.split(","));
	}

	private static String removeSpace(String input) {
		return input.replaceAll(" ", "");
	}

	private static void checkValidation(String input) {
		checkNull(input);
		checkLength(input);
		// if (checkNull(input) && checkLength(input)) {
		// 	return true;
		// }
		// return false;
	}

	private static boolean checkLength(String input) {
		if (Arrays.stream(input.split(","))
			.anyMatch(name -> name.length() > 5)) {
			throw new IllegalArgumentException("이름은 5자 이하여야 한다.");
			// OutputView.printOverLength();
			// return false;
		}
		return true;
	}

	private static void checkNull(String input) {
		if (input == null || input.isEmpty()) {
			//8. 그냥 thr나도록 던지고, getNames()호출부분에서
			// -> try-catch하고, 재귀로서 catch쉬 에러메세지 + 한번더 getNames호출되게 한다.
			throw new IllegalArgumentException("빈칸 입력은 허용하지 않는다.");
			// return false;
			// 7. throw랑 flag에 false리턴하는거랑 어떻게 동시에하지
			// OutputView.printNullError();
		}
		// System.out.println("전체 null은 통과");
		// Arrays.stream(input.split(","))
		// .map(String::length)
		// .mapToInt()
		// my) intstream으로 바꿀 때, "" -> length매긴것이 사라져버림.
		// -> 길이로 변환시에는 map(String::length) 이용할 것!!
		// .mapToInt( string -> string.length())
		// .boxed()
		// .collect(Collectors.toList())
		// .forEach(i -> System.out.println(i));
		// 5. TODO: 각각의 이름도 빈칸인 이름도 체크해야한다.
		// --> 14. 최종 : stream하면 뭐든 f,,,는 못잡고 ,,f는 잡는다
		// -> 그냥 개별 isEmpty 적용해서 잡자!!
		if (Arrays.stream(input.split(","))
			.anyMatch(String::isEmpty)) {
			// 	System.out.println("개별stream isEmpty로 비교");
			throw new IllegalArgumentException("빈칸 이름은 허용하지 않는다.");
			// OutputView.printNameNullError();
			// if (Arrays.asList(input.split(",")).contains("")) {
			// 	System.out.println("공백제거되었 때문에 contains('')에 걸림");
			// 	throw new IllegalArgumentException();
			// return false;
		}
		// System.out.println("각 null도 통과");

	}

	public static Integer getCount() {
		return 0;
	}

	private static boolean checkCountValidation(String input) {
		if (checkFormat(input) && checkRange(input)) {
			return true;
		}
		return false;
	}

	private static boolean checkFormat(String input) {
		// 6. 대박 string에 chars를 붙이면, IntStraem이다!!
		// -> char매직으로서, allMatCh(Character::isDigit)를 쓸 수 있따.
		return input.chars()
			.allMatch(Character::isDigit);
	}

	private static boolean checkRange(String input) {
		return false;
	}
}
