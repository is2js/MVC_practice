package racingCar2YoungyoonForStream.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Racing {
	private static final Racing racing = new Racing();
	// TODO: 싱글톤1) 메인로직에 사용되는 insert될 객채(혹은 list), 혹은 결과값(을 뽑아낼 수있음 생략) 변수와 객체를, 싱글톤이 관리.
	//private Cars cars;
	private Cars cars;

	private Racing() {
	}

	public static Racing getInstance() {
		return racing;
	}

	public void init() {
		// TODO: 로직용 관리할 변수, 결과변수 생길시마다 초기화넣어주기
		this.cars = null;
	}

	public void insert(Cars cars) {
		this.cars = cars;
	}

	public void game() {
		this.cars.playGame();
		this.cars.getRoundInfo();
	}

	public List<String> findNamesOfWinner() {
		List<Car> winners = this.cars.findWinners(findMaxPosition());
		return winners.stream()
			.map(Car::getName)
			.collect(Collectors.toList());

	}

	private int findMaxPosition() {
		return this.cars.findMaxPosition();
	}

	public String getNamesofWinners() {
		return this.cars.getNamesofWinners();
	}

	public String getRoundInfo() {
		return this.cars.getRoundInfo();
	}
}


