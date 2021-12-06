package racingCar1My.domain;

import java.util.Objects;

public class Ship implements Comparable<Ship> {
	//2) 단일객체가 가진 변수들  private 선언
	private final String name;
	private int position;

	//3) 생성자는 public으로 해도될듯. for 테스트 등
	// but 파라미터를 of,from 스태틱 메서드에서 검증된 것을 그대로 사용한다.
	// -> 굳이 모든 인변을 다 초기화 안해도된다. & position같은 경우, 생성자 호출전에 애초에 0으로 초기화하는 경우도.
	public Ship(String name) {
		// TODO: convert
		// this.name = name;
		this(name, 0);
	}

	public Ship(String name, int position) {
		//TODO: convert
		this.name = name;
		this.position = position;
	}
	//4) 생성자는 여러개(파라미터 받을 갯수 정해줄 수 있음)일 수 도 있다.

	//1)
	public static Ship of(String name, int i) {
		// TODO wrap 1) 필요시 검증
		// checkValidation(name);
		return new Ship(name, i);
	}

	@Override
	public int compareTo(Ship otherShip) {
		return Integer.compare(this.position, otherShip.toInt());

	}

	private int toInt() {
		return this.position;
	}

	@Override
	public String toString() {
		return "Ship{" +
			"name='" + name + '\'' +
			", position=" + position +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Ship ship = (Ship)o;
		return position == ship.position && Objects.equals(name, ship.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, position);
	}
}

//TODO: wrap 5) 단일객체부터는 비교를 해야하므로, equals && hashCode && toString 오버라이딩
//TODO: wrap 6) 추가로, 객체.compare메서드(객체) 객체끼리 비교시, 비교인변type에 따라서
// -> 사실 객체끼리 비교보단, 객체List.stream(). 집계 ( 단일::비교함수compareTo   ) .get(). toInt(); 시 필요해서 그렇다.
// (1) implements Comparable<$CLASS_NAME$> 이후
// (2) compareTo를 오버라이딩 -> 파라미터를 동일한 객체(  $CLASS_NAME$ other$CLASS_NAME$  ) 로 수정
// (3) return   Integer.compare( this.숫자인변 ,  other$CLASS_NAME$.toInt()    )    으로  비교하게 하기


