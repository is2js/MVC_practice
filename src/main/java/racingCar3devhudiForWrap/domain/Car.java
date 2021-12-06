package racingCar3devhudiForWrap.domain;

import java.util.Objects;
import java.util.stream.IntStream;

public class Car implements Comparable<Car> {

	//2) 단일객체가 가진 변수들  private 선언
	private final Name name;
	// private Position position;
	private Position position = new Position();
	private Movable movable;

	//3) 생성자는 public으로 해도될듯. for 테스트 등
	// but 파라미터를 of,from 스태틱 메서드에서 검증된 것을 그대로 사용한다.
	// -> 굳이 모든 인변을 다 초기화 안해도된다. & position같은 경우, 생성자 호출전에 애초에 0으로 초기화하는 경우도.
	// public Car(Name name) {
	// 	this.name = name;
	// }
	public Car(Name name, Movable movable) {
		this.name = name;
		this.movable = movable;
	}
	//4) 생성자는 여러개(파라미터 받을 갯수 정해줄 수 있음)일 수 도 있다.

	//1)
	// public static Car of(Name name) {
	public static Car of(Name name, Movable movable) {
		return new Car(name, movable);
	}

	//TODO: wrap 5) 단일객체부터는 비교를 해야하므로, equals && hashCode && toString 오버라이딩

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Car car = (Car)o;
		return position == car.position && Objects.equals(name, car.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, position);
	}

	@Override
	public int compareTo(Car o) {
		return Integer.compare(this.position.getPosition(), o.getCurrentPosition());
	}

	public String getName() {
		return this.name.getName();
	}

	// new4. 원래 랜덤값을 바깥에서 파라미터로 받아오려면, 상수가 들어오게 된다.
	// -> 랜덤이 포함된 코드를  외부에서 코드로 받을 수 있다 -> 인터페이스 -> 구현체impl Class  -> 조합용 구현객체.강제통일메서드()
	// 1) 코드를 받는 부분을 [인터페이스 참조변수]로 바꾼다.
	// 2) 원하는 코드를 구현한 class를 생성해서 -> 구현체 객체를 [인터페이스 참조변수]에 넣도록
	//    -> 파라미터를 추가한다
	// 3) 메서드 내부에서는 구현체객체.강제통일메서드()로 매번 새롭게 호출되게 한다.
	// 4) 필요하다면, 다른 코드를 구현한 구현체 class -> 구현체객체를 넣어도 된다.
	public void move() {
		// if (Util.getRandomNumber() >= 4)
		if (movable.isMovable())
			this.position = this.position.increase();
	}

	public int getCurrentPosition() {
		return this.position.getPosition();
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		IntStream.range(0, this.position.getPosition())
			.forEach(i -> stringBuilder.append("-"));

		return this.name.getName() + " : " + stringBuilder.toString();
	}

	public boolean getMaxPosition(int maxPosition) {
		return this.position.getPosition() == maxPosition;
	}

	//TODO: wrap 6) 추가로, 객체.compare메서드(객체) 객체끼리 비교시, 비교인변type에 따라서
	// -> 사실 객체끼리 비교보단, 객체List.stream(). 집계 ( 단일::비교함수compareTo   ) .get(). toInt(); 시 필요해서 그렇다.
	// (1) implements Comparable<Car> 이후
	// (2) compareTo를 오버라이딩 -> 파라미터를 동일한 객체(  Car otherCar  ) 로 수정
	// (3) return Integer.compare( this.숫자인변 , otherCar.getPosition());    으로  비교하게 하기

}
