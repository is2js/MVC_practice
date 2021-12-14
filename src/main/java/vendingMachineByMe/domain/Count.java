package vendingMachineByMe.domain;

import java.util.Objects;

public class Count implements Comparable<Count> {
	// TODO wrap 2) 단일객체가 가진 변수들  private 선언
	private int count;

	//TODO wrap2) 파라미터가 스태틱객체생성메서드(of, from)과 동일한지, 검증 추가 등으로 변경됬음 -> 여기도 변경
	public Count(String count) {
	    // 이미 스태틱함수에서 검증된 String -> int로 바로 convert by 유틸메서드
	    this.count = convertStringToInt(count); // 유틸로 이동시키기
	}

	private int convertStringToInt(String money) {
		return Integer.parseInt(money);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Count count1 = (Count)o;
		return count == count1.count;
	}

	@Override
	public int hashCode() {
		return Objects.hash(count);
	}

	//TODO wrap 1): 파라미터 검증(필요시) + reutrn 생성자 호출시 파라미터 추가 및 수정(default name)
	public static Count of(String count) {
		// 검증 필수!!
	    // checkCountValidation(count);
	    return new Count(count);
	}

	//TODO: wrap6) 내부 숫자인변의 증가, 감소 -> 다 메소드로 작성해야한다.
	//1) 증가 사용
	// this.position.incraese()
	//2) 메소드 정의
	// public Count increase() {
	// 	this.count++;
	// }

	// getter대신 toInt로 정의
	public int toInt() {
		return this.count;
	}

	@Override
	public int compareTo(Count o) {
		return Integer.compare( this.count , o.count);
	}
}
