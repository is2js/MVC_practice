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

	public static Balls from(String stringBalls) {
		checkValidation(stringBalls);

		return new Balls(stringBalls);
	}

	private static void checkValidation(String stringBalls) {
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
		return Scores.from(this.balls.stream().map(computerball::compare).collect(Collectors.toList()));
	}

	public Result compare(Balls computerBalls) {
		return Result.from(this.balls.stream().map(computerBalls::compare).collect(Collectors.toList()));
	}
}
