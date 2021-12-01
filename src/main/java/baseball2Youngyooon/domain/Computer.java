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

	// 싱글톤 객체생길때가 아니라, 매 게임마다(비교마다) 새롭게 초기화해주는 코드들을 init()으로 직접호출하게 한다.
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
