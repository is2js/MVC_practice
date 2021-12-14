package lotto2ByMe.domain;

import java.util.Objects;

public class Count {
	private Integer count;

	public Count(Integer count) {
		this.count = count;
	}

	public static Count of(Integer count) {
		return new Count(count);
	}

	//TODO: wrap 5) 숫자래핑의 객체vs객체 비교를 위해 택1

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

	public void decrease() {
		this.count--;
	}

	public int toInt() {
		return this.count;
	}

	public boolean isEnd() {
		if (this.count > 0) {
			this.decrease();
			return true;
		}
		return false;
	}
}
