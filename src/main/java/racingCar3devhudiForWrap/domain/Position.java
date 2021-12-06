package racingCar3devhudiForWrap.domain;

public class Position implements Comparable<Position> {
	// TODO wrap 2) 단일객체가 가진 변수들  private 선언
	private Integer position;

	public Position() {
		this.position = 0;
	}

	public Position(Integer position) {
		//TODO wrap3): convert
		this.position = position;
	}

	public static Position of(Integer position) {
		// TODO: 검증필요시 추가
		// checkPositionValidation(position);
		return new Position(position);
	}

	public int getPosition() {
		return this.position;
	}

	//new3. 숫자 래핑 객체의 증감(수정)은, << 수정된 값 -> 생성자에넣어 >>->  새로운 객체를 반환하는 식으로 한다.
	public Position increase() {
		// this.position = this.position +1;
		return new Position(this.position + 1);

	}

	@Override
	public int compareTo(Position otherPosition) {
		return Integer.compare(this.position, otherPosition.getPosition());
	}

	//TODO: wrap 5) 단일객체부터는 비교를 해야하므로, equals && hashCode && toString 오버라이딩

	//TODO: wrap 6) 추가로, 객체.compare메서드(객체) 객체끼리 비교시, 비교인변type에 따라서
	// -> 사실 객체끼리 비교보단, 객체List.stream(). 집계 ( 단일::비교함수compareTo   ) .get(). toInt(); 시 필요해서 그렇다.
	// (1) implements Comparable<Position> 이후
	// (2) compareTo를 오버라이딩 -> 파라미터를 동일한 객체(  Position otherPosition  ) 로 수정
	// (3) return Integer.compare( this.position , otherPosition.getPosition());    으로  비교하게 하기s

}
