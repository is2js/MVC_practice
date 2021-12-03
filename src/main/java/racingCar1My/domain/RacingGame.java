package racingCar1My.domain;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGame {
	private static final RacingGame racingGame = new RacingGame();
	private Cars cars;

	// public Cars cars;
	// public List<Integer> positions;
	public RacingGame() {
	}

	public static RacingGame getInstance() {
		return racingGame;
	}

	public void init() {
		// this.cars = new ArrayList<>();
		// this.positions = new ArrayList<>();
	}

	public void race() {
		//21. 맨위에서 그림그리기
		// 게임관리자 -> Cars -> 내부에서 this.cars.stream.각car에 함수적용-> car에서 this.활동
		//car마다, 랜덤수 외부에서 생성 -> 움직일수 있냐 판단 -> 움직이면 싱글톤한테
		// -> 데이터로 저장
		this.cars.moveCars();
	}

	public void setCars(Cars cars) {
		this.cars = cars;
	}

	public List<String> getResults() {
		return this.cars.getResults();
	}

	public List<String> findWinners() {
		List<Car> winnersCars = this.cars.getWinners();
		List<String> winnerNames = winnersCars.stream()
			.map(Car::getName)
			.collect(Collectors.toList());
		return winnerNames;
	}
}
