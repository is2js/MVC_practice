package racingCar1My.domain;

import java.util.stream.IntStream;

import racingCar1My.utils.Util;

/**
 * 다음 Car 객체를 활용해 구현해야 한다.
 * Car 기본 생성자를 추가할 수 없다.
 * name, position 변수의 접근 제어자인 private을 변경할 수 없다.
 * 가능하면 setPosition(int position) 메소드를 추가하지 않고 구현한다.
 */
public class Car implements Comparable<Car> {
	private final String name;
	private int position = 0;

	public Car(String name) {
		this.name = name;
	}

	public static Car of(String name) {
		//TODO: 검증
		return new Car(name);
	}

	public void move() {
		int randomNumber = Util.generateRandomNumber();
		if (randomNumber >= 4) {
			this.position++;
		}

	}

	public String getResult() {
		// String result_hyphen = String.valueOf(Stream.generate(() -> "-").limit(this.position).reduce((a, b) -> a + b));
		// - 이걸로 하면.. Optional부텅서 출력된다...
		StringBuilder hyphen = new StringBuilder();
		for (int i = 0; i < this.position; i++) {
			hyphen.append("-");
		}
		String result = this.name + " : " + hyphen;
		return result;
	}

	public int getPosition() {
		return this.position;
	}

	public String getName() {
		return this.name;
	}

	public String getHyphen() {
		return IntStream.range(0, this.position)
			.mapToObj((i) -> "-")
			.reduce((a, b) -> (a + b))
			.orElse(""); // reduce -> get의 결과는 Optional이라.. orElse( default값 ) 지정안해주면, NoSuchElement에러난다. get()대신 orElse("") ;
	}

	@Override
	public int compareTo(Car o) {
		return Integer.compare(this.position, o.getPosition());
	}

	// // 추가 기능 구현
	// pobi : --
	// woni : ----
	// jun : ---

}
