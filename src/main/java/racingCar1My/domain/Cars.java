package racingCar1My.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Cars {
	private List<Car> cars;

	// public Cars(List<Car> cars) {
	private Cars(List<String> names) {
		//convert? (객체 생성의 stream으로)
		// 18. 포장클래스Lit인 일급콜렉션의 생성자에서는 뭘할지?> Balls를 보자.
		// 1) String을 int로 convert, 2) int를 Ball객체들로  convert한다
		// --> 포장클래스List인  일급컬렉션 의 생성자 목적은 of,from등 스태틱메소드에서 검증한 파라미터로
		// --> 포장클래스 Car들을 여러개 만들어 넣는 것.
		// --> names의 갯수대로 객체를 만들어야하므로, IntStream.range + 파라미터size로 돌리면서
		// ---> 그 갯수로 돌리는 것은 똑같을 듯? ㄴㄴ..어차피 index가 직접적으로 이용xx
		// --> 변환만 필요하면, 갯수는 자동으로맞춰진다. -> IntStream(index도 하나의 정보일때, 객체가 list의 한 숫자들일 때)
		this.cars = names.stream()
			//19. Car.of 만들러 갈 듯.
			.map(name -> Car.of(name))
			.collect(Collectors.toList());

		// List<Integer> integerBalls = Util.ConvertStrToIntList(stringBalls);
		// this.balls = IntStream.range(0, integerBalls.size())
		// 	.mapToObj(i -> Ball.of(i, integerBalls.get(i)))
		// 	.collect(Collectors.toList());

	}

	public static Cars from(List<String> names) {
		//TODO: 파라미터 검증
		return new Cars(names);
	}

	public List<String> getResults() {
		return this.cars.stream()
			.map(car -> car.getResult())
			.collect(Collectors.toList());
	}

	public void moveCars() {
		this.cars
			.forEach(Car::move);
	}

	public List<Car> getWinners() {
		int maxPosition = 0;
		for (Car car : cars) {
			maxPosition = Math.max(maxPosition, car.getPosition());
		}

		//22.findAny()는 Stream에서 가장 먼저 탐색되는 요소를 리턴하고, findFirst()는 조건에 일치하는 요소들 중에 Stream에서 순서가 가장 앞에 있는 요소를 리턴합니다.
		// -> 복수일때는 둘다 쓰면 안됨..ㅠㅠ 병렬처리시에만 차이
		int finalMaxPosition = maxPosition;
		return this.cars.stream()
			.filter(car -> car.getPosition() == finalMaxPosition)
			.collect(Collectors.toList());
	}
}
