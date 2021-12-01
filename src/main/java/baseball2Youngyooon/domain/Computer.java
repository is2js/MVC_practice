package baseball2Youngyooon.domain;

import java.util.LinkedHashSet;

import camp.nextstep.edu.missionutils.Randoms;

public class Computer {
	private static Computer computer = new Computer();
	private Balls balls;

	private Computer() {
	}

	public static Computer getInstance() {
		return computer;
	}

	public void init() {
		// balls = Balls.from(makeRandomNumbers());
		balls = Balls.from("123");
	}

	private String makeRandomNumbers() {
		LinkedHashSet<Integer> hashSet = new LinkedHashSet<>();
		while (hashSet.size() < 3) {
			int randomNumber = Randoms.pickNumberInRange(1, 9);
			hashSet.add(randomNumber);
		}
		return hashSet.stream().map(Object::toString).reduce((a, b) -> a + b).get();
	}

	public Result matchBalls(String inputBalls) {
		return balls.compare(Balls.from(inputBalls));
	}

}
