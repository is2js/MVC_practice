package baseball3streamAndEnum.domain;

import java.util.Objects;

public class Value implements Comparable<Value>{
	private Integer value;

	public Value(Integer value) {
	    this.value = value;
	}
	public static Value of(Integer value) {
	    return new Value(value);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Value value1 = (Value)o;
		return Objects.equals(value, value1.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public int compareTo(Value o) {
		return Integer.compare(this.value, o.getValue());
	}

	public Integer getValue() {
		return value;
	}
}
