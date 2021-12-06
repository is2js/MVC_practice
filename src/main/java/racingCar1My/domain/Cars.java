package racingCar1My.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {
	//2) private한 객체List를 감싸고 있는데, 관리대상인 원시타입List를 변수로 작성한다.
	private List<Car> cars;

	//3) 스태틱from( List 외부파라미터 )로부터 검증된(+ 동일한) 외부파라미터(List)를 private(-> 테스트나 기타 등을 위해서 public으로 변경)생성자에 배정한다.
	// -> 외부list를 stream()map()에서 객체로 변환하여 list로 collect한다.
	public Cars(List<String> names) {
		//TODO: convert (포맷 ->) 외부 list.stream().map(  element ->  *객체 List가 되도록 단일객체생성Car.of(  element  )*     ).collect(Collectors.toList())
		// -> map속에서 단일객체.of( )를 또 정리하러 가자.
		this.cars = names.stream()
			.map(name -> Car.of(name))
			.collect(Collectors.toList());

		// cf) map 속 단일객체생성시 index도 필요하면, IntStream + mapToObj  -> IntStream.range(0, list.size()).mapToObj(   i -> Ball.of(i, list.get(i))    )
		// List<Integer> integerBalls = Util.ConvertStrToIntList(stringBalls);
		// this.balls = IntStream.range(0, integerBalls.size())
		// 	.mapToObj(i -> Ball.of(i, integerBalls.get(i)))
		// 	.collect(Collectors.toList());
	}

	//1) 객체List 매핑한 일급컬렉션 -> 스태틱생성메서드에서 검증은 똑같은데, 외부 파라미터도 List로 들어와야한다.
	// -> 객체 매핑 : of( String or Int)  / 객체 List매핑 : from ( List<> xxx ) -> 여긴 아마 바깥 파라미터로 인하 자동 생성될듯싶다.
	// -> 특별값을 반환해야하는 경우만, 들어갈 경우 if분기 return new 클래스( 특정값 );
	public static Cars from(List<String> names) {
		//TODO: 일급컬렉션) 파라미터 검증(필요시)
		// checkValidation(names);
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
		// -> filter 쓴 뒤 걸러서 collect로 모으든지 or forEach로 각각 처리해야됨.
		// -> 복수일때는 둘다 쓰면 안됨..ㅠㅠ 병렬처리시에만 차이
		int finalMaxPosition = maxPosition;
		return this.cars.stream()
			.filter(car -> car.getPosition() == finalMaxPosition)
			.collect(Collectors.toList());
	}

	public LinkedHashMap<String, String> getRound() {
		return this.cars.stream()
			.collect(Collectors.toMap(
				Car::getName,
				Car::getHyphen,
				(Old, New) -> (Old),
				LinkedHashMap::new
			));
	}
}
