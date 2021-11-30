package baseball2Youngyooon.domain;

import java.util.Objects;

public class Index {
	public static final int MIN_INDEX = 0;
	public static final int MAX_INDEX = 2;
	//2. 기존 원시타입 변수를  인스턴스 변수로 가지게 하면서
	private int index;

	//3. 생성자에서 원시값을 받아 -> 예외처리를 해버린다.
	public Index(int index) {
		//4. 벨리데이션은 checkXXX -> 내부에서 !바른범위 -> thr한다.
		checkRangeValid(index);
		//5. check if not thr 벨리데이션 통과시, this.원시변수에 넣어준다.
		this.index = index;
	}

	private void checkRangeValid(int index) {
		if (!isValidRange(index)) {
			throw new IllegalArgumentException("0~2사이 범위를 입력하세요.");
		}
	}

	private boolean isValidRange(int index) {
		return MIN_INDEX <= index && index <= MAX_INDEX;
	}

	//6. 감싸쭌 클래스의 equals비교시..  기준을 .index로 만들기
	// 1) 완전히 == 동일객체면 true반환
	// 2) if 필터링 개념: null이 들어오거나 or 클래스정보가 다른 객체가 들어오면 false
	// 3) 완전히 같은객체(다른인변까지 다 똑같은..?)가 아니고 null아니고  클래스 정보는 같다
	// -> 형변환한 뒤, .index의 값이 같으면 같은 거라고 true리턴해준다.
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ( obj == null | this.getClass() != obj.getClass()) {
			return false;
		}
		// 4) 위에 필터링에 안걸렸다면 비교한다. -> 형변환후 .index값만 같으면 간다
		Index obj1 = (Index)obj;
		return this.index == obj1.index;
	}

	//7. equals뿐만 아니라 hashCode()메서드의 기준도 .index로 만들기
	// -> java.lang이 아닌 .uitl에 있는 Objectsssss를 가져와서 처리함.
	// -> hashCode비교시 index릐 해쉬코드를 날려주게 한다.
	@Override
	public int hashCode() {
		return Objects.hash(this.index);
	}
}
