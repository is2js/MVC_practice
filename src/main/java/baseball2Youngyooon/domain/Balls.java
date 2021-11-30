package baseball2Youngyooon.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import baseball2Youngyooon.utils.Util;

public class Balls {

	private List<Ball> balls;

	private Balls(String stringBalls) {
		List<Integer> integerBalls = Util.ConvertStrToIntList(stringBalls);
		this.balls = IntStream.range(0, integerBalls.size())
			.mapToObj(i -> Ball.of(i, integerBalls.get(i)))
			.collect(Collectors.toList());
	}

	public static Balls of(String stringBalls) {
		// 1. of(외부 문자열숫자 파라미터)로 스태틱메소드로 객체생성 한다면,
		// -> private생성자는 1) convert + 2) 객체list로 변환로직은 그대로두고
		// -> of스태틱메소드에  생성자호출전 검증로직을 추가한다.
		// -> 개별 checkXXX()메소드 -> 내부 thr 작전 전에, 통로로서 일단 만듬.
		checkValidation(stringBalls);

		return new Balls(stringBalls);
	}

	private static void checkValidation(String stringBalls) {
		//2. 길이, 포맷, 범위, 중복여부 순이다.
		checkLength(stringBalls);
		checkFormat(stringBalls);
		// checkRange(stringBalls);
		checkDuplicates(stringBalls);
	}

	private static void checkDuplicates(String stringBalls) {

		if (isDuplicates(stringBalls)) {
			throw new IllegalArgumentException("중복된 숫자가 없도록 입력해주세요.");
		}
	}

	private static boolean isDuplicates(String stringBalls) {
		return stringBalls.length() != Arrays.stream(stringBalls.split("")).distinct().count();
	}

	private static void checkFormat(String stringBalls) {
		//3. 포맷(숫자입력) 확인은 Character.iDigit()를 활용하기 위해,   string.toCharArray()를 iter로 돌려 확인함.
		for (char numberString : stringBalls.toCharArray()) {
			if (!isDigit(numberString)) {
				throw new IllegalArgumentException("숫자를 입력하세요.");
			}
		}
	}

	private static boolean isDigit(char numberString) {
		return Character.isDigit(numberString);
	}

	private static void checkLength(String stringBalls) {
		if (!isValidLength(stringBalls)) {
			throw new IllegalArgumentException("3자리 숫자만 입력해주세요.");
		}
	}

	private static boolean isValidLength(String stringBalls) {
		return stringBalls.length() == 3;
	}

	public Scores compare(Ball computerball) {
		return Scores.of(this.balls.stream().map(computerball::compare).collect(Collectors.toList()));
	}

	public Result compare(Balls computerBalls) {
		return Result.of(this.balls.stream().map(computerBalls::compare).collect(Collectors.toList()));
	}
}
