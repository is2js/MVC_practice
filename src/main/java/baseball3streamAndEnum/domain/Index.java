package baseball3streamAndEnum.domain;

import java.util.Objects;

public class Index implements Comparable<Index> {
	private final Integer index;

	public Index(Integer index) {
		this.index = index;
	}

	public static Index of(Integer index) {
		return new Index(index);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Index index1 = (Index)o;
		return Objects.equals(index, index1.index);
	}

	@Override
	public int hashCode() {
		return Objects.hash(index);
	}

	public Integer getIndex() {
		return index;
	}

	@Override
	public int compareTo(Index o) {
		return Integer.compare(this.index, o.getIndex());
	}
}
