package racingCar3devhudiForWrap.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Cars {

	//2) private한 객체List를 감싸고 있는데, 관리대상인 원시타입List를 변수로 작성한다.
	private List<Car> cars;

	//3) 스태틱from( List 외부파라미터 )로부터 검증된(+ 동일한) 외부파라미터(List)를 private생성자에 배정한다.
	// -> 외부list를 stream()map()에서 객체로 변환하여 list로 collect한다.
	public Cars(Names names) { //TODO: from와 동일한 파라미터로 변경해주기
		//TODO: convert (포맷 ->) 외부 names.stream().map(  element ->  *객체 List가 되도록 단일객체생성Car.of(  element  )*     ).collect(Collectors.toList())
		// -> map속에서 단일객체.of( )를 또 정리하러 가자.
		this.cars = names.getNames().stream()
			// .map(Car::of)
			.map(name -> Car.of(name, new OneMovable()))
			// -> IntStream.range(0, names.size()).mapToObj(i ->     Ball.of( i ,   names.get(i)  ))
			.collect(Collectors.toList());

		// cf) map 속 단일객체생성시 index도 필요하면, IntStream + mapToObj  -> IntStream.range(0, names.size()).mapToObj(   i -> Ball.of(i, names.get(i))    )
		// List<Integer> integerBalls = Util.ConvertStrToIntList(stringBalls);
		// this.balls = IntStream.range(0, integerBalls.size())
		// 	.mapToObj(i -> Ball.of(i, integerBalls.get(i)))
		// 	.collect(Collectors.toList());
	}

	//1) 객체List 매핑한 일급컬렉션 -> 스태틱생성메서드에서 검증은 똑같은데, 외부 파라미터도 List로 들어와야한다.
	// -> 객체 매핑 : of( String or Int)  / 객체 List매핑 : from ( List<> xxx ) -> 여긴 아마 바깥 파라미터로 인하 자동 생성될듯싶다.
	// -> 특별값을 반환해야하는 경우만, 들어갈 경우 if분기 return new 클래스( 특정값 );
	public static Cars from(Names names) {
		//TODO: 일급컬렉션) 파라미터 검증(필요시)
		// checkCarsValidation(names);
		return new Cars(names);
	}

	public void move() {
		this.cars
			.forEach(Car::move);
	}

	public LinkedHashMap<String, Integer> printResult() {
		return this.cars.stream()
			.collect(Collectors.toMap(
				Car::getName,
				Car::getCurrentPosition,
				(Old, New) -> Old,
				LinkedHashMap::new
			));

	}

	public String getResult() {
		return this.cars.stream()
			.map(Car::toString)
			.collect(Collectors.joining(System.lineSeparator()));
		// NEW

		// return this.cars.stream()
		// 	.collect(Collectors.toMap(
		// 		Car::getName,
		// 		Car::getCurrentPosition,
		// 		(Old, New) -> Old,
		// 		LinkedHashMap::new
		// 	));
	}

	public List<Car> findWinners() {
		// new2. max(기준)을 만족하는 객체 -> 1개밖에 못골라내나보다..
		// -> max를 만족하는 모든 객체들은 나누서 구해야하나보다..
		// -> intStream 바로 max값 -> fitler로 max랑 같은 것들

		int maxPosition = this.cars.stream()
			.mapToInt(Car::getCurrentPosition)
			.max()
			.orElseThrow(NoSuchElementException::new);

		List<Car> maxPositionCars = this.cars.stream()
			.filter(car -> car.getMaxPosition(maxPosition))
			.collect(Collectors.toList());

		return maxPositionCars;
	}
}
