package baseball2Youngyooon.domain;

import java.util.Objects;

public class Index {
	public static final int MIN_INDEX = 0;
	public static final int MAX_INDEX = 2;
	private int index;

	public Index(int index) {
		checkRangeValid(index);
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ( obj == null | this.getClass() != obj.getClass()) {
			return false;
		}
		Index obj1 = (Index)obj;
		return this.index == obj1.index;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.index);
	}
}
