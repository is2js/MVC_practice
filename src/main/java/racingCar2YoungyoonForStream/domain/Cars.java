package racingCar2YoungyoonForStream.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Cars {

	//2) private한 객체List를 감싸고 있는데, 관리대상인 원시타입List를 변수로 작성한다.
	private List<Car> cars;

	public Cars(List<String> list) { //TODO: from와 동일한 파라미터로 변경해주기
		this.cars = list.stream()
			.map(element -> Car.of(element))
			.collect(Collectors.toList());
	}

	public static Cars from(List<String> list) {
		// checkValidation(list);
		return new Cars(list);
	}

	public void playGame() {
		this.cars
			.forEach(car -> car.game());
	}

	// public Car getMaxPosition() {
	// 	return this.cars.stream() // 집계를 하려면, 하위 단일객체마다, 객체끼리 int비교해준ㄴ compareTo메서드가 필요하다.
	// 		.max(Car::compareTo)
	// 		.get(); // max(Car::compareTo).get()의 결과는 단일객체다.
	// }

	public int findMaxPosition() {
		return this.cars.stream()
			.max(Car::compareTo) // 집계함수의 결과도 reduce처럼 Optional이다. -> get보다는.. orElseThrow()로 가야할듯?
			.get()
			.getPosition();
	}

	public List<Car> findWinners(int maxPosition) {
		// 조건절에서 getter 부등호는 직접 메서들 작성한다.
		return this.cars
			.stream()
			.filter(car -> car.isSamePosition(maxPosition))
			.collect(Collectors.toList());
	}

	//
	// 해당하는 객체만 가져온 상태
	// -> 객체를 sb.append( ) 등 문자열 구간에 넣는 순간 toString이 작동하니까
	// -> toString에 현재 포지션까지 같이 다 넣어버리면?
	public String getNamesofWinners() {
		StringBuilder result = new StringBuilder();
		this.cars.stream()
			.filter(car -> car.isSamePosition(findMaxPosition()))
			.forEach(maxPositionCar -> {
				result.append(maxPositionCar).append(System.lineSeparator());
			});
		return result.toString();
	}

	public String getRoundInfo() {
		StringBuilder info = new StringBuilder();
		// return이 아닌 forEach로 자동차마다 일을 시켜서 데이터를 append함
		this.cars
			.stream()
			.forEach(car -> info.append(car));
		// 이 때, car객체 자체를 문자열 라인에 넣는다? -> toString 오버라이딩한 것을 가져가니 거기에 정의해주자.
		// info.append(System.lineSeparator());
		return info.toString();
	}
	//추가
}
