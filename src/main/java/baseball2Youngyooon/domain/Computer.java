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
		balls = Balls.from(makeRandomNumbers());
	}

	private String makeRandomNumbers() {
		LinkedHashSet<Integer> hashSet = new LinkedHashSet<>();
		while (hashSet.size() < 3) {
			int randomNumber = Randoms.pickNumberInRange(1, 9);
			hashSet.add(randomNumber);
		}
		return hashSet.stream().map(Object::toString).reduce((a, b) -> a + b).get();
	}

	//5. 외부에서 사용될 예정인 준 컨트롤러 메서드는 input으로 String을 받게한다.
	public Result matchBalls(String inputBalls) {
		//6. Balls.from으로 Balls객체끼리 비교하도록 하자.
		return balls.compare(Balls.from(inputBalls));
	}

}
