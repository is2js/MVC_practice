package racingCar3devhudiForWrap.domain;

import java.util.List;

public class RacingGame {
	private static final RacingGame racingGame = new RacingGame();
	// TODO: 싱글톤1) 메인로직에 사용되는 insert될 객채(혹은 list), 혹은 결과값(을 뽑아낼 수있음 생략) 변수와 객체를, 싱글톤이 관리.
	private Cars cars;

	// 2) 싱글톤 private생성자는 비워둠
	private RacingGame() {
	}

	//3) 외부에서 이미생성된 싱글톤을 부르는 스태틱메서드
	public static RacingGame getInstance() {
		return racingGame;
	}

	//4) 관리하는 insert된 변수들, 결과값 변수들 초기화
	public void init() {
		// TODO: 로직용 관리할 변수, 결과변수 생길시마다 초기화넣어주기
		insertCars(null);
	}

	public void startGame(Cars cars) {
		//  // 조심 insert는 게임시작 전에 넣어야함.
		moveCars();
	}



	private void moveCars() {
		this.cars.move();
	}

	public String getResult() {
		return this.cars.getResult();
	}

	public List<Car> findWinners() {
		return this.cars.findWinners();
	}

	public void insertCars(Cars cars) {
		this.cars = cars;
	}
}
