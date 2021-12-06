package racingCar3devhudiForWrap.domain;

import java.util.Objects;

public class Count implements Comparable<Count> {
	// TODO wrap 2) 단일객체가 가진 변수들  private 선언
	private Integer count;

	public Count(Integer count) {
		//TODO wrap3): convert
		this.count = count;
	}
	//TODO wrap 4) 생성자는 여러개(파라미터 받을 갯수 정해줄 수 있음)일 수 도 있다.

	//TODO wrap 1): 파라미터 검증(필요시) + 파라미터 추가 및 수정(default name)
	public static Count of(Integer count) {
		// TODO: 검증필요시 추가
		// checkCountValidation(count);
		return new Count(count);
	}

	public boolean isRacingEnd() {
		//new7. count볏누 내부에서는 this.count를 써서 편하게 값을 조정한다.
		// n부터 시작하여, 1까지만 반복하며, 물어볼때? 바로 --를 때리게 한다.
		// -> 들어온상황에서는 1 -> 들어오자마자 물어보면서 0 -> 다음번에 못들어온다.
		//-> 원래는 -1 업데이트하고 바로 검사가 좋지만, 뒤에  count 관련로직이 아예없으니
		// -> 조건식에만 등장하는 변수라면, 검사직후 업데이트를 바로 해도된다.(원래는 업데이트직후 검사)
		if (this.count > 0) {
			//new8. --;의 작업도, 매핑변수이므로 return new 새 객체로 한번해볼까
			// -> next()는 내부에서 --1하는 것이므로.. 외부에서 객체갈아끼기기 아니므로,
			// 내부에서는 새 객체를 반환해선 안됨..
			this.next();
			//new9. 아직 1이상이면, true를 반환해서 loop가 계속 돌게 한다.
			return true;

		}
		// new9. 특정조건을 만족시키지 못하는 경우, false만 반환하면 알아서 바깥루프가 종료된다.
		return false;
	}

	private void next() {
		// new10. 외부 [[조건식에서의 증감]]은, 새객체로 할당이 안되므로, 새객체 반환은 못한다.
		this.count--;
	}

	//TODO: wrap 5) 단일객체부터는 비교를 해야하므로, equals && hashCode && toString 오버라이딩

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Count count1 = (Count)o;
		return Objects.equals(count, count1.count);
	}

	@Override
	public int hashCode() {
		return Objects.hash(count);
	}

	@Override
	public int compareTo(Count otherCount) {
		return Integer.compare(this.count, otherCount.getCount());
	}

	public int getCount() {
		return this.count;
	}

	//TODO: wrap 6) 추가로, 객체.compare메서드(객체) 객체끼리 비교시, 비교인변type에 따라서
	// -> 사실 객체끼리 비교보단, 객체List.stream(). 집계 ( 단일::비교함수compareTo   ) .get(). toInt(); 시 필요해서 그렇다.
	// (1) implements Comparable<test> 이후
	// (2) compareTo를 오버라이딩 -> 파라미터를 동일한 객체(  Count otherCount  ) 로 수정
	// (3) return Integer.compare( this.count , otherCount.ge());    으로  비교하게 하기s

}
