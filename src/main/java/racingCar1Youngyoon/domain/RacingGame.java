package racingCar1Youngyoon.domain;

import java.util.ArrayList;
import java.util.List;

public class RacingGame {
	private static RacingGame racingGame = new RacingGame();

	private List<Car> cars;

	public RacingGame() {
	}

	public static RacingGame getInstance() {
		return racingGame;
	}

	public void init() {
		cars = new ArrayList<>();
	}
}
