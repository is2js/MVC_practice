package racingCar2YoungyoonForStream.domain;

import java.util.Objects;
import java.util.stream.IntStream;

public class Car implements Comparable<Car> {
	private final String name;
	private int position = 0;

	public Car(String name) {
		//TODO: convert
		this.name = name;
	}

	public static Car of(String name) {
		// checkValidation(name);
		return new Car(name);
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
	public int compareTo(Car otherCar) {
		return Integer.compare(this.position, otherCar.getPosition());
	}

	protected int getPosition() {
		return this.position;
	}

	protected String getName() {
		return this.name;
	}

	public boolean isSamePosition(int maxPosition) {
		return this.position == maxPosition;
	}

	//TODO: wrap 6) 추가로, 객체.compare메서드(객체) 객체끼리 비교시, 비교인변type에 따라서
	// -> 사실 객체끼리 비교보단, 객체List.stream(). 집계 ( 단일::비교함수compareTo   ) .get(). toInt(); 시 필요해서 그렇다.
	// (1) implements Comparable<Car> 이후
	// (2) compareTo를 오버라이딩 -> 파라미터를 동일한 객체(  Car otherCar  ) 로 수정
	// (3) return   Integer.compare( this.숫자인변 ,  otherCar.toInt()    )    으로  비교하게 하기

	@Override
	public String toString() {
		return name + " : " + this.currentPositionLength() + System.lineSeparator();
	}

	private String currentPositionLength() {
		// return IntStream.range(0, this.position)
		// 	.mapToObj((i) -> "-")
		// 	.reduce((a, b) -> (a + b))
		// 	// .get();
		// 	.orElse(""); // reduce의 결과는 Optional<String>이라.. default값을 넣어주자.
		//
		StringBuilder stringBuilder = new StringBuilder();
		IntStream.range(0, this.position)
			.forEach(i -> stringBuilder.append("-"));
		return stringBuilder.toString();

	}

	public void game() {
	}
}
